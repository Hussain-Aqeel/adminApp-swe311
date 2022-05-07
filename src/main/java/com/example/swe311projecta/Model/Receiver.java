package com.example.swe311projecta.Model;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Receiver extends Thread{
    private static final String[] protocols = new String[]{"TLSv1.3"};
    private static final String[] cipher_suites = new String[]{"TLS_AES_128_GCM_SHA256"};
    private SSLServerSocket sslServerSocket;
    private Admin admin;

    public Receiver(Admin admin) {
        this.admin = admin;
    }

    public void start(int port) throws IOException {
        sslServerSocket = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(port);
        sslServerSocket.setEnabledCipherSuites(cipher_suites);
        sslServerSocket.setEnabledProtocols(protocols);
        sslServerSocket.setNeedClientAuth(false);
       }
       public void run(){
        System.out.println("Starting Listening At: "+ admin.getIp() +":"+ admin.getPort());
           while (true) {
               try {
                   new EchoClientHandler((SSLSocket) sslServerSocket.accept()).start();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }


    private  class EchoClientHandler extends Thread {
        private SSLSocket clientSocket;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        public EchoClientHandler(SSLSocket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {


            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            Object o=in.readObject();
            if(o instanceof Contact)
            {
            admin.receiveContact((Contact) o);

            }
            out.writeObject("OK");


            in.close();
            out.close();
            clientSocket.close();}
            catch (Exception e){



            }
        }
    }



}

