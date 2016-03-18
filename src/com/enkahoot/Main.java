package com.enkahoot;
import com.enkahoot.User.KahootUser;
import com.enkahoot.gui.UserCreatorWizard;
import com.enkahoot.web.KahootSession;

public class Main {

    public static String gameID = "10000";
    public static void main(String[] args) {
	    UserCreatorWizard main = new UserCreatorWizard();
        new KahootUser("billy");
    }
}
