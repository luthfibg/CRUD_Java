package com.uas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataProcess extends javax.swing.JFrame {

    DefaultTableModel tableModel;
    ResultSet resultSet = null;

    public JPanel getMainPanel(){
        return BasePanel;
    }

    public DataProcess(){
        frameInit();
        showData();
    }

    private void showData() {
        try {
            Object[] columnTitle = {"course_name", "course_num", "enrollment", "start_date", "end_date"};
            tableModel = new DefaultTableModel(null, columnTitle);
            jTable.setModel(tableModel);
            // retrieve mySQL DB
            Connection connect = Connector.ConnectDB();
            Statement stt = connect.createStatement();
            tableModel.getDataVector().removeAllElements();
            // set query to fetch data
            resultSet = stt.executeQuery("SELECT * FROM tbcourse");
            while (resultSet.next()) {
                Object[] data = {
                        resultSet.getString("course_name"),
                        resultSet.getString("course_number"),
                        resultSet.getString("enrollment"),
                        resultSet.getString("start_date"),
                        resultSet.getString("end_date")
                };
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private JPanel mainPanel;
    private JPanel BasePanel;
    private JPanel titlePanel;
    private JLabel jTitlePanel;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private JPanel tablePanel;
    private JTextField courseNameTextField;
    private JTextField courseNumberTextField;
    private JTextField enrollmentTextField;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton buttonUpdate;
    private JTable jTable;
    private JLabel courseNameLabel;
    private JLabel courseNumberLabel;
    private JLabel enrollmentLabel;
    private JLabel endDateLabel;
    private JLabel startDateLabel;
}
