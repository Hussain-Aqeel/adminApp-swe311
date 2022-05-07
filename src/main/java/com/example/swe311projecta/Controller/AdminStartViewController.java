package com.example.swe311projecta.Controller;

import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.ViewModel.AdminStartUpViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;

public class AdminStartViewController {
  File file;
  ViewHandler viewHandler;
  AdminStartUpViewModel adminStartUpViewModel;
  
  @FXML
  private TextField adminIp;
  
  @FXML
  private PasswordField adminPassword;
  
  @FXML
  private TextField adminPort;
  
  @FXML
  private Button enterBtn;
  
  public void init(ViewHandler viewHandler, AdminStartUpViewModel adminStartUpViewModel) {

    this.viewHandler=viewHandler;
    this.adminStartUpViewModel=adminStartUpViewModel;

    adminIp.textProperty().bindBidirectional(adminStartUpViewModel.ipProperty());
    adminPassword.textProperty().bindBidirectional(adminStartUpViewModel.passwordProperty());
    StringConverter s=new IntegerStringConverter();
    adminPort.textProperty().bindBidirectional(adminStartUpViewModel.portProperty(),s);
    adminIp.setText("127.0.0.1");
  }
  
  @FXML
  void enterAdmin(ActionEvent event) {
  adminStartUpViewModel.createAdmin();
    viewHandler.showAdminView();
  }



    public void selectFile(ActionEvent actionEvent) {
      FileChooser fileChooser=new FileChooser();
      file=fileChooser.showOpenDialog(new Popup());

    }

  public void loadFile(ActionEvent actionEvent) {
    if (file != null)
    {
    try {
      adminStartUpViewModel.loadAdmin(file);
      viewHandler.showAdminView();
    } catch (Exception e) {
      e.printStackTrace();
    }}

  }
}

