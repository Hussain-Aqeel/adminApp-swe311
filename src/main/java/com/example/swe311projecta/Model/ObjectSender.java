package com.example.swe311projecta.Model;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;


public class ObjectSender extends Thread{

    private static final String[] protocols = new String[]{"TLSv1.3"};
    private static final String[] cipher_suites = new String[]{"TLS_AES_128_GCM_SHA256"};
    private SSLSocket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Object data;
    private String address;
    private int port;


    public ObjectSender( Object data, String address, int port) {

        this.data = data;
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            startConnection();
        sendMessage();
        stopConnection();

        } catch (CertificateException | IOException | KeyStoreException | NoSuchAlgorithmException |
                 ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void startConnection() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {

        clientSocket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(address,port);
        clientSocket.setEnabledCipherSuites(cipher_suites);
        clientSocket.setEnabledProtocols(protocols);
        clientSocket.setWantClientAuth(false);

        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void sendMessage() throws IOException, ClassNotFoundException {


        out.writeUnshared(data);
        Object o=in.readObject();
        if (o.equals("OK"))
            System.out.println("OK");

    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static boolean checkIfOnline(String address,int port) {
        try{
            SSLSocket cs = (SSLSocket) SSLSocketFactory.getDefault().createSocket(address,port);
            cs.setEnabledCipherSuites(cipher_suites);
            cs.setEnabledProtocols(protocols);
            cs.setWantClientAuth(false);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}

