package com.example.swe311projecta.ViewModel;

import com.example.swe311projecta.Core.ViewModelFactory;
import com.example.swe311projecta.Model.Admin;
import com.example.swe311projecta.Model.FileIO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;

public class AdminStartUpViewModel {
    private ViewModelFactory viewModelFactory;
    private FileIO fileIO;
    private Admin admin;
    private StringProperty ip=new SimpleStringProperty();
    private IntegerProperty port=new SimpleIntegerProperty();
    private StringProperty password=new SimpleStringProperty();
    public AdminStartUpViewModel(ViewModelFactory viewModelFactory, FileIO fileIO, Admin admin) {
        this.fileIO = fileIO;
        this.admin  = admin;
        this.viewModelFactory=viewModelFactory;
    }
    public void createAdmin(){
        admin=new Admin(ip.get(),port.get(),password.getValue());
        viewModelFactory.getModelFactory().setAdmin(admin);

    }

    public String getIp() {
        return ip.get();
    }

    public StringProperty ipProperty() {
        return ip;
    }

    public int getPort() {
        return port.get();
    }

    public IntegerProperty portProperty() {
        return port;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void loadAdmin(File file) throws Exception{
        fileIO.setFile(file);
        admin=fileIO.fileToAdmin(password.get());
        admin.init(password.get());
        viewModelFactory.getModelFactory().setAdmin(admin);
    }
}
