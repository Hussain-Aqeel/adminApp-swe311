package com.example.swe311projecta.Model;

import lombok.Data;

import java.io.Serializable;

@Data

public class Contact implements Serializable {
    private String ip;
    private String name;
    private int port;
    private transient boolean Online;
    private Chat chat;

    public Contact(String ip, String name, int port) {
        this.ip = ip;
        this.name = name;
        this.port = port;
        chat=new Chat();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                ", port=" + port +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!getIp().equals(contact.getIp())) return false;
        return getName().equals(contact.getName());
    }

    @Override
    public int hashCode() {
        int result = getIp().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
