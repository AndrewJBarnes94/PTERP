package com.brocodesoftware.pterp_client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplicationController {

    @FXML
    public void createUserTable() {
        try (Connection conn = DatabaseManager.connect(); Statement stmt = conn.createStatement()) {
            // Create users table if it doesn't exist
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL,
                    password TEXT NOT NULL
                )
            """);
            System.out.println("Table 'users' created or already exists");

        } catch (SQLException e) {
            System.out.println("SQLException caught: " + e.getMessage());
        }
    }

    @FXML
    protected void openLogin() {
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
