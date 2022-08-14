package com.uas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {

    public UpdateData() {
        jButtonUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String upCourseId, upCourseName, upCourseNum, upCourseEnroll, upCourseStart, upCourseEnd;
                upCourseId = jTextUpId.getText();
                upCourseName = jTextUpName.getText();
                upCourseNum = jTextUpNum.getText();
                upCourseEnroll = jTextUpEnroll.getText();
                upCourseStart = jTextUpStart.getText();
                upCourseEnd = jTextUpEnd.getText();

                try {
                    pstUpdate = Connector.ConnectDB().prepareStatement("UPDATE tbcourse SET course_name = ?, course_number = ?, enrollment = ?, start_date = ?, end_date = ? WHERE id_course = ?");
                    pstUpdate.setString(1, upCourseName);
                    pstUpdate.setString(2, upCourseNum);
                    pstUpdate.setString(3, upCourseEnroll);
                    pstUpdate.setString(4, upCourseStart);
                    pstUpdate.setString(5, upCourseEnd);
                    pstUpdate.setString(6, upCourseId);

                    pstUpdate.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Update data success!");
                    JComponent component = (JComponent) e.getSource();
                    Window window = SwingUtilities.getWindowAncestor(component);
                    window.dispose();

                } catch (SQLException exception){
                    exception.printStackTrace();
                }
            }
        });
        jButtonCancelUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getUpdatePanel(){
        return updatePanel;
    }

    public PreparedStatement pstUpdate;
    private JPanel updatePanel;
    private JLabel jUpdateTitle;
    private JTextField jTextUpId;
    private JTextField jTextUpName;
    private JLabel jLabelId;
    private JLabel jLabelCName;
    private JLabel jLabelEnrollment;
    private JLabel jLabelCNum;
    private JLabel jLabelCEnd;
    private JLabel jLabelCStart;
    private JButton jButtonUp;
    private JButton jButtonCancelUp;
    private JTextField jTextUpNum;
    private JTextField jTextUpEnroll;
    private JTextField jTextUpStart;
    private JTextField jTextUpEnd;
}
