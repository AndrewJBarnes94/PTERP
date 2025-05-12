package com.brocodesoftware.pterp_client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AppController {

    @FXML
    private TreeView<String> processTreeView;

    @FXML
    public void initialize() {
        createUserTable();

        // Build business process tree
        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);

        TreeItem<String> financials = new TreeItem<>("Financials");
        financials.getChildren().addAll(
                new TreeItem<>("Accounts Payable"),
                new TreeItem<>("Accounts Receivable"),
                new TreeItem<>("Billing"),
                new TreeItem<>("Cash Management"),
                new TreeItem<>("Cost Accounting"),
                new TreeItem<>("General Ledger"),
                new TreeItem<>("Taxes"),
                new TreeItem<>("Multi-Currency"),
                new TreeItem<>("Currency Translation")
        );

        TreeItem<String> operations = new TreeItem<>("Operations");
        operations.getChildren().addAll(
                new TreeItem<>("Inventory"),
                new TreeItem<>("Bill of Material"),
                new TreeItem<>("Part Tracker"),
                new TreeItem<>("Job Tracker"),
                new TreeItem<>("Project Tracker"),
                new TreeItem<>("Material Planning"),
                new TreeItem<>("Production Control"),
                new TreeItem<>("Purchasing"),
                new TreeItem<>("Shipping")
        );

        TreeItem<String> sales = new TreeItem<>("Sales & CRM");
        sales.getChildren().addAll(
                new TreeItem<>("Customer Quotes"),
                new TreeItem<>("Customer Orders"),
                new TreeItem<>("Sales Analysis"),
                new TreeItem<>("Deductive Management")
        );

        TreeItem<String> controls = new TreeItem<>("Controls");
        controls.getChildren().addAll(
                new TreeItem<>("Log Parse")
        );

        TreeItem<String> misc = new TreeItem<>("Utilities & Admin");
        misc.getChildren().addAll(
                new TreeItem<>("Executive"),
                new TreeItem<>("Cross Applications"),
                new TreeItem<>("Utilities")
        );

        root.getChildren().addAll(financials, operations, sales, controls, misc);
        processTreeView.setRoot(root);
        processTreeView.setShowRoot(false);

        // Optional: Add selection behavior
        processTreeView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.isLeaf()) {
                System.out.println("Selected process: " + newVal.getValue());
                // Future: load view in center pane
            }
        });
    }

    @FXML
    public void createUserTable() {
        try (Connection conn = DatabaseManager.connect(); Statement stmt = conn.createStatement()) {
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
