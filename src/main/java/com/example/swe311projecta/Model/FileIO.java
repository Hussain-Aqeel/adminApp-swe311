package com.example.swe311projecta.Model;

import lombok.Data;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Data
public class FileIO {
    private  File file = new File("user.data");
    
    public  Admin fileToUser(String password) throws IOException, ClassNotFoundException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
        SealedObject sealedObject= (SealedObject) objectInputStream.readObject();
        objectInputStream.close();

        return  encryption.decAdmin(password,sealedObject);
    }
    
    
    public void saveAdmin(Admin user,String password) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
        SealedObject sealedObject=encryption.encryptAdmin(password,user);
        objectOutputStream.writeObject(sealedObject);
        objectOutputStream.close();


    }

}
