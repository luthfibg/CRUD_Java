package com.uas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    private JPanel deletePanel;
    private JLabel jDeleteTitle;
    private JTextField jTextDel;
    private JButton jButtonCancelDel;
    private JButton jButtonDelete;
    private JLabel jLabelDeleteId;
    public PreparedStatement pstDelete;


    public JPanel getDeletePanel(){
        return deletePanel;
    }

    public DeleteData() {
        jButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String defineID;
                defineID = jTextDel.getText();
                try {
                    pstDelete = Connector.ConnectDB().prepareStatement("DELETE FROM tbcourse WHERE id_course = ?;");
                    pstDelete.setString(1, defineID);

                    pstDelete.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data deleted!");
                    JComponent component = (JComponent) e.getSource();
                    Window window = SwingUtilities.getWindowAncestor(component);
                    window.dispose();

                } catch (SQLException exception){
                    exception.printStackTrace();
                }
            }
        });
        jButtonCancelDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
