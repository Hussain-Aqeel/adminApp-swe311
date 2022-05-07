package com.example.swe311projecta.ViewModel;

import com.example.swe311projecta.Model.Admin;
import com.example.swe311projecta.Model.Contact;
import com.example.swe311projecta.Model.ContactStatus;
import com.example.swe311projecta.Model.FileIO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

public class AdminViewModel {
    private Admin admin;
    private FileIO fileIO;
    private ObservableList<Contact> approvedContact;
    private ObservableList<String> contactStatuses;
    private ObservableList<Contact> pendingContact;
    private StringProperty name;
    private StringProperty ip;
    private IntegerProperty port;
    private StringProperty onlineCount;
    public ObservableList<String> getContactStatuses() {
        return contactStatuses;
    }

    public AdminViewModel(Admin admin, FileIO fileIO) {
        this.admin = admin;
        this.fileIO = fileIO;
        approvedContact= FXCollections.observableArrayList();
        pendingContact=FXCollections.observableArrayList();
        contactStatuses=FXCollections.observableArrayList();
        approvedContact.setAll(admin.getApprovedContacts());
        pendingContact.setAll(admin.getPendingList());
        contactStatuses.setAll(admin.getStatusList());
        name=new SimpleStringProperty();
        ip=new SimpleStringProperty();
        port=new SimpleIntegerProperty();
        onlineCount=new SimpleStringProperty(admin.numOfOnlineOverTotal());

    }

    public ObservableList<Contact> getApprovedContact() {
        return approvedContact;
    }

    public ObservableList<Contact> getPendingContact() {
        return pendingContact;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
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

    public void refresh5s(){
        admin.updateHashMap();
        refresh();
    }

    public void refresh(){

        approvedContact.setAll(admin.getApprovedContacts());
        pendingContact.setAll(admin.getPendingList());
        contactStatuses.setAll(admin.getStatusList());
        onlineCount.setValue(admin.numOfOnlineOverTotal());
        admin.sendApprovedContact();
    }

    public void creatNewContact() {
        admin.approveContact(new Contact(getIp(),getName(),getPort()));
        refresh();
    }

    public void approveContact(Contact selectedItem) {
        admin.approveContact(selectedItem);
        refresh();
    }

    public void deleteContact(Contact contact) {

        admin.deleteContact(contact);
        refresh();
    }
    public void rejectContact(Contact contact){
        admin.rejectContact(contact);
        refresh();
    }

    public void saveAdmin() {
        fileIO.setFile(new File("admin.data"));
        try {
            fileIO.saveAdmin(admin,admin.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public String getOnlineCount() {
        return onlineCount.get();
    }

    public StringProperty onlineCountProperty() {
        return onlineCount;
    }
}
