package com.enkahoot;
import com.enkahoot.User.KahootUser;
import com.enkahoot.gui.UserCreatorWizard;
import com.enkahoot.web.KahootSession;
import com.enkahoot.web.UserActionListener;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static String gameID = "10000";
    public static UserActionListener mimicMaster;
    public static void main(String[] args) throws IOException {
        //cross platform magic right here...
        /*
        if(System.getProperty("os.name").contains("Windows")) {
            Runtime.getRuntime().exec("cmd.exe phantomjs.exe --webdriver= 8910");
        }
        else //linux master raccceee!
        {
            Runtime.getRuntime().exec("/bin/sh phantomjs --webdriver= 8910");
        }
        */
        gameID = JOptionPane.showInputDialog("Enter game ID:");
	    UserCreatorWizard main = new UserCreatorWizard();
        mimicMaster = new UserActionListener();
    }
}
