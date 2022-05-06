package com.example.swe311projecta.Model;

import lombok.Data;

@Data
public class ContactStatus {
    private int uptime;
    private boolean online;

    public ContactStatus() {
        uptime=0;
        online=false;
    }

    @Override
    public String toString() {
        return "ContactStatus{" +
                "uptime=" + uptime +
                ", online=" + online +
                '}';
    }
}
