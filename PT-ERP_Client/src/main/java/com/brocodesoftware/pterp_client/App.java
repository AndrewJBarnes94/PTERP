package com.brocodesoftware.pterp_client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load login window first
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent loginRoot = loginLoader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(loginRoot, 320, 190));
            loginStage.initModality(Modality.APPLICATION_MODAL); // Prevent access to other windows
            loginStage.showAndWait(); // Wait until login window closes

            // If login was successful, continue to main app
            if (LoginSession.isLoggedIn()) {
                FXMLLoader appLoader = new FXMLLoader(getClass().getResource("application-view.fxml"));
                Scene appScene = new Scene(appLoader.load(), 1280, 700);
                primaryStage.setTitle("PT-ERP");
                primaryStage.setScene(appScene);
                primaryStage.show();
            } else {
                // Exit if user didn't log in
                System.exit(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
