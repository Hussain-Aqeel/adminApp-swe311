module com.example.adminapp {
  requires javafx.controls;
  requires javafx.fxml;
  
  requires org.controlsfx.controls;
  
  opens com.example.adminapp to javafx.fxml;
  opens com.example.adminapp.Controller to javafx.fxml;
  exports com.example.adminapp;
  exports com.example.adminapp.Controller;
}