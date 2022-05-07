package com.example.swe311projecta.Core;


import com.example.swe311projecta.AdminApplication;
import com.example.swe311projecta.Controller.AdminDashboardController;
import com.example.swe311projecta.Controller.AdminStartViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    private final ViewModelFactory vmf;
    private final Stage stage;
    private Scene StartUpScene;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
        stage = new Stage();
    }
    public void start(){
        openStartUpView();
        stage.show();
    }

    private void openStartUpView() {
        try {
        FXMLLoader loader=new FXMLLoader();


        loader.setLocation(getClass().getResource("/fxml/AdminStartView.fxml"));

            Parent root = loader.load();
            AdminStartViewController startUpController=loader.getController();

            startUpController.init(this,vmf.getStartUpViewModel());
            StartUpScene=new Scene(root);

            stage.setTitle("Admin");
            stage.setResizable(false);

            stage.setScene(StartUpScene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void showAdminView() {
        try {
        FXMLLoader loader=new FXMLLoader();


        loader.setLocation(getClass().getResource("/fxml/AdminDashboard.fxml"));

        Parent root = loader.load();



        AdminDashboardController controller=loader.getController();

        controller.init(this,vmf.getAdminViewModel());
        Scene scene=new Scene(root);


        stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("what");
        stage.show();
    }
}
