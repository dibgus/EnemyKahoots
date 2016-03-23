package com.enkahoot;

import com.enkahoot.gui.UserCreatorWizard;
import com.enkahoot.web.BrowserType;
import com.enkahoot.web.UserActionListener;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static String gameID = "10000";
    public static UserActionListener mimicMaster;
    public static ExecutorService threads = Executors.newCachedThreadPool();
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
        BrowserType choice = BrowserType.valueOf(((String)JOptionPane.showInputDialog(null, "Select preferred browser:", "Select Browser", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Firefox", "Chrome", "Opera", "ie"}, "Browsers")).toUpperCase());
        gameID = JOptionPane.showInputDialog("Enter game ID:");
	    UserCreatorWizard main = new UserCreatorWizard();
        mimicMaster = new UserActionListener(choice);
    }
}
