package com.uas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteData {
    private JPanel deletePanel;
    private JLabel jDeleteTitle;
    private JTextField jTextDel;
    private JButton jButtonCancelDel;
    private JButton jButtonDelete;
    private JLabel jLabelDeleteId;

    public JPanel getDeletePanel(){
        return deletePanel;
    }

    public DeleteData() {
        jButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
