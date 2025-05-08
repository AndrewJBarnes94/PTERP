package com.brocodesoftware.pterp_client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationController {

    @FXML
    protected void openLogin()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/brocodesoftware/pterp_client/login-view.fxml"));
            Parent loginRoot = loader.load();
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(loginRoot, 320, 190));
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}