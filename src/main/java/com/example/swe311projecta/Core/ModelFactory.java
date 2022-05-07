package com.example.swe311projecta.Core;

import com.example.swe311projecta.Model.Admin;
import com.example.swe311projecta.Model.FileIO;
import lombok.Setter;

@Setter
public class ModelFactory {
    private FileIO fileIO;
    private Admin admin;

    public Admin getAdmin(){

        return admin;
    }
    public FileIO getFileIO(){
        if (fileIO == null) {
            fileIO=new FileIO()   ;
        }
        return fileIO;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        this.admin.listen();

    }
}
