package com.uas;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataProcess extends javax.swing.JFrame {

    DefaultTableModel tableModel;
    ResultSet resultSet = null;

    PreparedStatement pst;

    public JPanel getMainPanel(){
        return BasePanel;
    }

    public DataProcess(){
        frameInit();
        showData();
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName, courseNumber, enrollment, startDate, endDate;
                courseName = courseNameTextField.getText();
                courseNumber = courseNumberTextField.getText();
                enrollment = enrollmentTextField.getText();
                startDate = startDateTextField.getText();
                endDate = endDateTextField.getText();

                try {
                    pst = Connector.ConnectDB().prepareStatement("INSERT INTO tbcourse (course_name, course_number, enrollment, start_date, end_date) values (?,?,?,?,?)");
                    pst.setString(1, courseName);
                    pst.setString(2, courseNumber);
                    pst.setString(3, enrollment);
                    pst.setString(4, startDate);
                    pst.setString(5, endDate);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data successfully added");
                    showData();
                } catch (SQLException err){
                    Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, err);
                }
                courseNameTextField.setText("");
                courseNumberTextField.setText("");
                enrollmentTextField.setText("");
                startDateTextField.setText("");
                endDateTextField.setText("");
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName;
                courseName = courseNameTextField.getText();
                try {
                    pst = Connector.ConnectDB().prepareStatement("DELETE FROM tbcourse WHERE course_name = ?;");
                    pst.setString(1, courseName);
                    JOptionPane.showMessageDialog(null, "Data Deleted");
                    showData();
                    courseNameTextField.setText("");
                    courseNumberTextField.setText("");
                    enrollmentTextField.setText("");
                    startDateTextField.setText("");
                    endDateTextField.setText("");
                } catch(SQLException err){
                    err.printStackTrace();
                }
            }
        });
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(DataProcess::createUpdateGUI);

                Object[] columnTitle = {"id_course", "course_name", "course_num", "enrollment", "start_date", "end_date"};
                tableModel = new DefaultTableModel(null, columnTitle);
//                tableModel.fireTableDataChanged();
//
//                jTable.revalidate();
//                jTable.repaint();
                showData();

//                String courseNameUp,courseName, courseNumber, enrollment, startDate, endDate;
//                courseNameUp = courseNameTextField.getText();
//                courseName = courseNameTextField.getText();
//                courseNumber = courseNumberTextField.getText();
//                enrollment = enrollmentTextField.getText();
//                startDate = startDateTextField.getText();
//                endDate = endDateTextField.getText();
//
//                try {
//                    pst = Connector.ConnectDB().prepareStatement("UPDATE tbcourse SET course_name = ?, course_number = ?, enrollment = ?, start_date = ?, end_date = ? WHERE course_name = ?");
//                    pst.setString(1, courseName);
//                    pst.setString(2, courseNumber);
//                    pst.setString(3, enrollment);
//                    pst.setString(4, startDate);
//                    pst.setString(5, endDate);
//
//                    pst.executeUpdate();
//                    JOptionPane.showMessageDialog(null, "Update data success!");
//                    showData();
//
//                } catch (SQLException exception){
//                    exception.printStackTrace();
//                }
            }
        });

        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String key = searchTextField.getText();
                System.out.println(key);

                if (!Objects.equals(key, "")){
                    searchData(key);
                } else {
                    showData();
                }

            }
        });
    }

    private void showData() {
        try {
            Object[] columnTitle = {"id_course", "course_name", "course_num", "enrollment", "start_date", "end_date"};
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
                        resultSet.getString("id_course"),
                        resultSet.getString("course_name"),
                        resultSet.getString("course_number"),
                        resultSet.getString("enrollment"),
                        resultSet.getString("start_date"),
                        resultSet.getString("end_date")
                };
                tableModel.addRow(data);
            }
            tableModel.fireTableDataChanged();


        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void searchData(String key) {
        try {
            Object[] columnTitle = {"id_course", "course_name", "course_num", "enrollment", "start_date", "end_date"};
            tableModel = new DefaultTableModel(null, columnTitle);
            jTable.setModel(tableModel);
            // retrieve mySQL DB
            Connection connect = Connector.ConnectDB();
            Statement stt = connect.createStatement();
            tableModel.getDataVector().removeAllElements();
            // set query to fetch data
            resultSet = stt.executeQuery("SELECT * FROM tbcourse WHERE course_name LIKE '%"+key+"%' OR course_number LIKE '%"+key+"%' OR enrollment LIKE '%"+key+"%' OR start_date LIKE '%"+key+"%' OR end_date LIKE '%"+key+"%' ");
            while (resultSet.next()) {
                Object[] data = {
                        resultSet.getString("id_course"),
                        resultSet.getString("course_name"),
                        resultSet.getString("course_number"),
                        resultSet.getString("enrollment"),
                        resultSet.getString("start_date"),
                        resultSet.getString("end_date")
                };
                tableModel.addRow(data);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void createUpdateGUI(){

        UpdateData UpdateUI = new UpdateData();
        JPanel updateRoot = UpdateUI.getUpdatePanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(updateRoot);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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
    private JScrollPane jScrollTable;
    private JTextField searchTextField;
    private JLabel searchLabel;
}
