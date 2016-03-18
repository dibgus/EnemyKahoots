package com.enkahoot.User;

import com.enkahoot.Main;
import com.enkahoot.web.KahootSession;

/**
 * Created by Ivan on 3/17/2016.
 */
public class KahootUser {
    private String userName;
    private KahootSession session;

    public KahootUser(String userName)
    {
        this.userName = userName;
        session = new KahootSession(this, Main.gameID);
    }

    public String getUserName()
    {
        return userName;
    }
}
