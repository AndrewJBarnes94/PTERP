package com.brocodesoftware.pterp_client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;

    @FXML
    private void onLoginClick() {
        PasswordHasher password = new PasswordHasher(passwordField.getText());
        System.out.println(password.hash());
    }
}
