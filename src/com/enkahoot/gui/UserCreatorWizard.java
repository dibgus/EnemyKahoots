package com.enkahoot.gui;

import com.enkahoot.Main;
import com.enkahoot.User.KahootUser;
import com.enkahoot.User.UserType;
import com.enkahoot.web.KahootSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ivan on 3/17/2016.
 */
public class UserCreatorWizard {
    private JTextField txtName;
    private JButton addButton;
    private JTextField txtGID;
    private JRadioButton rbtnMimic;
    private JRadioButton rbtnSmart;
    private JRadioButton rbtnRandom;
    private JCheckBox lockGIDCheckBox;
    private JPanel panel;
    private JRadioButton rbtnDead;
    private JTextArea lblUsers;

    private JFrame display = new JFrame();
    public UserCreatorWizard()
    {
        display.setContentPane(panel);
        lockGIDCheckBox.addActionListener(new LockListener());
        addButton.addActionListener(new AddButtonListener());
        txtGID.setText(Main.gameID);
        display.pack();
        setVisible(true);
    }

    public void setVisible(boolean visible)
    {
        display.setVisible(visible);
    }


    private class LockListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            txtGID.setEditable(!lockGIDCheckBox.isSelected());
        }
    }

    private class AddButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(!txtName.getText().equals("") || !txtGID.getText().equals("")) {

                UserType assign;
                if(rbtnRandom.isSelected())
                    assign = UserType.RANDOM;
                else if(rbtnMimic.isSelected())
                    assign = UserType.MIMIC;
                else if(rbtnDead.isSelected())
                    assign = UserType.DEAD;
                else
                    assign = UserType.SMART;

                KahootUser user = new KahootUser(txtName.getText(), assign);
                if (lblUsers.getText().equals("Users: "))
                    lblUsers.setText(lblUsers.getText() + txtName.getText() + "(" + assign.type + ")");
                else
                    lblUsers.setText(lblUsers.getText() + ", " + txtName.getText() + "(" + assign.type + ")");
                txtName.setText("");
            }
        }
    }
}
