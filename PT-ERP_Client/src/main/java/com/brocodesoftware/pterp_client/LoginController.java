package com.brocodesoftware.pterp_client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;

    @FXML
    private void onLoginClick() {
        String username = usernameField.getText();
        String rawPassword = passwordField.getText();

        PasswordHasher hasher = new PasswordHasher(rawPassword);
        String hashedPassword = hasher.hash();
        System.out.println("Hashed Password: " + hashedPassword);

        loginUser(username, hashedPassword);
    }

    private void loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful for user: " + username);

                // Close current Create Account window
                Stage currentStage = (Stage) usernameField.getScene().getWindow();
                currentStage.close();

            } else {
                System.out.println("Login failed: invalid credentials");
            }

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }
    }

    @FXML
    private void onNavigateToCreateAccount() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/brocodesoftware/pterp_client/create-account-view.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Create Account");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
