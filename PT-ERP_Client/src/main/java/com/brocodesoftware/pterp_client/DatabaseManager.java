package com.brocodesoftware.pterp_client;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_FOLDER = "data";
    private static final String DB_FILENAME = "pterp_client.db";
    private static final String DB_PATH = DB_FOLDER + File.separator + DB_FILENAME;
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;

    static {
        // Ensure the folder exists
        File directory = new File(DB_FOLDER);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to SQLite database at " + DB_PATH);
            return conn;
        } catch (SQLException e) {
            System.err.println("Connection to SQLite database failed:");
            e.printStackTrace();
            return null;
        }
    }
}
