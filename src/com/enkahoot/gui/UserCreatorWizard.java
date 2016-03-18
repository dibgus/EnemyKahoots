package com.enkahoot.gui;

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
    private JRadioButton mimicMRadioButton;
    private JRadioButton triviaGeniusGRadioButton;
    private JRadioButton randomoniumRRadioButton;
    private JCheckBox lockGIDCheckBox;
    private JPanel panel;

    private JFrame display = new JFrame();
    public UserCreatorWizard()
    {
        display.setContentPane(panel);
        lockGIDCheckBox.addActionListener(new LockListener());
        addButton.addActionListener(new AddButtonListener());
        //display.pack();
        setVisible(true);
    }

    public void setVisible(boolean visible)
    {
        display.setVisible(visible);
    }

    private class AddButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class LockListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            txtGID.setEditable(!lockGIDCheckBox.isSelected());
        }
    }
}
