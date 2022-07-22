package com.uas;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static Connection connectDB;
    public static Connection ConnectDB() throws SQLException {
        try {
            String DB = "jdbc:mysql://localhost/javacode";
            String user = "root";
            String pass = "";

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connectDB = DriverManager.getConnection(DB, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Database Connection", "Error!", JOptionPane.INFORMATION_MESSAGE);
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return connectDB;
    }


}
