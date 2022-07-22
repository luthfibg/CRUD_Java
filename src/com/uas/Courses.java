package com.uas;

import javax.swing.*;

public class Courses {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Courses::createGUI);
    }
    public static void createGUI(){

        DataProcess UI = new DataProcess();
        JPanel root = UI.getMainPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
