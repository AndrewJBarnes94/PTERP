package com.brocodesoftware.pterp_client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateAccountController {
    @FXML
    private TextField newUsernameField;
    @FXML
    private PasswordField newPasswordField;

    @FXML
    private void onCreateAccountClick() {
        String username = newUsernameField.getText();
        String rawPassword = newPasswordField.getText();
        PasswordHasher password = new PasswordHasher(rawPassword);
        String hashedPassword = password.hash();

        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();
            System.out.println("User '" + username + "' added");

            // Close current Create Account window
            Stage currentStage = (Stage) newUsernameField.getScene().getWindow();
            currentStage.close();

            // Open Login window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/brocodesoftware/pterp_client/login-view.fxml"));
            Parent loginRoot = loader.load();
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(loginRoot, 320, 190));
            loginStage.show();

        } catch (SQLException e) {
            System.out.println("User '" + username + "' could not be added: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onNavigateToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/brocodesoftware/pterp_client/login-view.fxml"));
            Stage stage = (Stage) newUsernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
