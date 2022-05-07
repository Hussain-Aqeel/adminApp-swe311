package com.example.swe311projecta;

import com.example.swe311projecta.Core.ModelFactory;
import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.Core.ViewModelFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    System.setProperty("javax.net.ssl.keyStore","keys/myKeyStore.jks");

    System.setProperty("javax.net.ssl.trustStore","keys/myTrustStore.jts");

    // System.setProperty("javax.net.debug","all");
    System.setProperty("javax.net.ssl.trustStorePassword","123456");
    System.setProperty("javax.net.ssl.keyStorePassword","123456");
    ViewHandler viewHandler=new ViewHandler(new ViewModelFactory(new ModelFactory()));
    viewHandler.start();
  }
  
  public static void main(String[] args) {
    launch();
  }
}