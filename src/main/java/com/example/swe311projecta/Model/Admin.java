package com.example.swe311projecta.Model;

import lombok.Data;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Data
public class Admin implements Serializable {
    private ArrayList<Contact> approvedContacts;
    private ArrayList<Contact> pendingList;
    private transient Receiver receiver;
    private String  ip;
    private int port;
    private transient String password;
    private transient HashMap<Contact,ContactStatus> hashMap;
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("javax.net.ssl.keyStore","myKeyStore.jks");

        System.setProperty("javax.net.ssl.trustStore","myTrustStore.jts");

        // System.setProperty("javax.net.debug","all");
        System.setProperty("javax.net.ssl.trustStorePassword","123456");
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
        Admin admin=new Admin("127.0.0.1",5555,"s");

        admin.listen();
        while (true){
            admin.sendApprovedContact();

            Thread.sleep(60000);
            ((ArrayList<Contact>)admin.pendingList.clone()).forEach(contact -> {
            admin.approveContact(contact);
            });
            admin.pendingList.clear();
            admin.hashMap.forEach((contact, contactStatus) ->
                    System.out.println(contact +"\n"+contactStatus)
                    );
            admin.updateHashMap();
        }

    }

    public Admin(String ip, int port,String password) {
        this.ip = ip;
        this.port = port;
        this.password=password;
        this.hashMap=new HashMap<>();
        approvedContacts=new ArrayList<>();
        pendingList=new ArrayList<>();
        receiver=new Receiver(this);
    }
    public void init(String password) {
        receiver = new Receiver(this);
        this.password = password;
        approvedContacts.forEach(contact -> hashMap.put(contact,new ContactStatus()));

    }

    public void receiveContact(Contact o) {
        System.out.println("Contact Recived");
        if (
                approvedContacts.contains(o)|| pendingList.contains(o)
        )
            return;
        pendingList.add(o);
        pendingList.forEach(contact -> System.out.println(contact));
    }
    public void listen(){
        try {
            receiver.start(port);
            receiver.setDaemon(true);
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void approveContact(Contact contact){
        if (pendingList.remove(contact)){
            approvedContacts.add(contact);
            hashMap.put(contact,new ContactStatus());

        }

    }
    public void rejectContact(Contact contact){
        if (pendingList.remove(contact)){
            approvedContacts.remove(contact);

        }

    }
    public void deleteContact(Contact contact){

        if(approvedContacts.contains(contact)){
            approvedContacts.remove(contact);
            hashMap.remove(contact);
        }

    }
    public void sendApprovedContact(){
        approvedContacts.forEach(contact -> {ObjectSender objectSender=new ObjectSender(approvedContacts,contact.getIp(),contact.getPort());
            objectSender.start();

        });
    }
    public void updateHashMap(){
        hashMap.forEach((contact, contactStatus) -> {
            Thread t = new Thread() {
                public void run() {
                    boolean online=ObjectSender.checkIfOnline(contact.getIp(),contact.getPort());
                    System.out.println(online);
                    if(online)
                    {

                        contactStatus.setOnline(true);
                        contactStatus.setUptime(contactStatus.getUptime()+60);
                    }
                    else{

                        contactStatus.setOnline(false);
                        contactStatus.setUptime(0);
                    }
                }
            };
            t.start();



        });


    }
}
