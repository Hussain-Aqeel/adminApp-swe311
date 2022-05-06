module com.example.adminapp {
  requires javafx.controls;
  requires javafx.fxml;
  
  requires org.controlsfx.controls;
    requires lombok;

    opens com.example.swe311projecta to javafx.fxml;
  opens com.example.swe311projecta.Controller to javafx.fxml;
  exports com.example.swe311projecta;
  exports com.example.swe311projecta.Controller;
}