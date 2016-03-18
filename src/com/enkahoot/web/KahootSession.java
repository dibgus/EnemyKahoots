package com.enkahoot.web;

import com.enkahoot.User.KahootUser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.text.html.HTML;

/**
 * Created by Ivan on 3/17/2016.
 */
public class KahootSession implements Runnable{
    private HtmlUnitDriver headlessSession = new HtmlUnitDriver();
    private KahootUser user;
    private String gameID;
    public KahootSession(KahootUser user, String gameID)
    {
        this.user = user;
        headlessSession.get("http://kahoot.it");
        System.out.println("testing: " + headlessSession.getPageSource());
        this.gameID = gameID;
        new Thread(this).run();
    }

    public void enterGame()
    {
        String currentPath = headlessSession.getCurrentUrl();
        WebElement pinbox = headlessSession.findElement(By.id("inputSession"));
        pinbox.sendKeys(gameID + "\n"); //todo test if \n or physical click is required

        while(!currentPath.equals(headlessSession.getCurrentUrl()))
            System.out.println("waiting..."); //todo replace this with a do-nothing

        WebElement nameBox = headlessSession.findElement(By.id("nameBox")); //todo get actual id of name input box
        nameBox.sendKeys(user.getUserName());
    }

    @Override
    public void run() {
        enterGame();
    }
}
