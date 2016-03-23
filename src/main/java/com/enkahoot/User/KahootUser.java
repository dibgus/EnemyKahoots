package com.enkahoot.User;

import com.enkahoot.Main;
import com.enkahoot.web.KahootSession;

/**
 * Created by Ivan on 3/17/2016.
 */
public class KahootUser {
    private String userName;
    private KahootSession session;

    public KahootUser(String userName, UserType type)
    {
        this.userName = userName;
        session = new KahootSession(this, type, Main.gameID);
    }

    public String getUserName()
    {
        return userName;
    }
}
