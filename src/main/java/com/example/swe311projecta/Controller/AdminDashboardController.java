package com.example.swe311projecta.Controller;

import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.Model.Contact;
import com.example.swe311projecta.ViewModel.AdminViewModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class AdminDashboardController {
  public ListView<Contact> approvedList;
  public ListView contactStatus;
  public Label numOfOnline;
  AdminViewModel adminViewModel;
  
  @FXML
  private Button approveBtn;
  
  @FXML
  private Button createButton;
  

  
  @FXML
  private TextField ip;
  
  @FXML
  private TextField name;
  
  @FXML
  private ListView<Contact> pendingList;
  
  @FXML
  private TextField port;
  
  void init(){
  
  }
  
  @FXML
  void CreateNewUser(ActionEvent event) {
    adminViewModel.creatNewContact();
  
  }
  
  @FXML
  void DownloadReport(ActionEvent event) {
  
  }
  
  @FXML
  void approveNewUser(ActionEvent event) {
    if(pendingList.getSelectionModel().getSelectedItem()!=null){
      adminViewModel.approveContact(pendingList.getSelectionModel().getSelectedItem());


    }
  
  }

  public void init(ViewHandler viewHandler, AdminViewModel adminViewModel) {
    this.adminViewModel=adminViewModel;
    this.pendingList.setItems(adminViewModel.getPendingContact());
    this.approvedList.setItems(adminViewModel.getApprovedContact());
    contactStatus.setItems(adminViewModel.getContactStatuses());
    numOfOnline.textProperty().bindBidirectional(adminViewModel.onlineCountProperty());
    ip.textProperty().bindBidirectional(adminViewModel.ipProperty());
    name.textProperty().bindBidirectional(adminViewModel.nameProperty());
    port.textProperty().bindBidirectional(adminViewModel.portProperty(),(StringConverter) new IntegerStringConverter());
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> adminViewModel.refresh5s()));

    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();



  }

  public void deleteContact(ActionEvent actionEvent) {
    Contact contact=approvedList.getSelectionModel().getSelectedItem();
    if (contact != null) {
      approvedList.getSelectionModel().clearSelection();
      adminViewModel.deleteContact(contact);

    }

  }

  public void reject(ActionEvent actionEvent) {

    Contact contact=pendingList.getSelectionModel().getSelectedItem();
    if (contact != null) {
      pendingList.getSelectionModel().clearSelection();
      adminViewModel.rejectContact(contact);

    }
  }

  public void saveAd(ActionEvent actionEvent) {
    adminViewModel.saveAdmin();

  }
}

