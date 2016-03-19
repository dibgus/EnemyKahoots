package com.enkahoot.web;

import com.enkahoot.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Ivan on 3/17/2016.
 * The main class to determine the main user's answers to copy
 */
public class UserActionListener implements Runnable{
    private WebDriver userSession;
    private String answerData = "";
    public UserActionListener()
    {
        new Thread(this).start();
    }
    //<div class="message  answerA" ng-class="messageClass()">
    public void answerFetch()
    {
        WebElement ele = userSession.findElement(By.xpath("//*"));
        String rawData = ele.getAttribute("outerHTML");
        if(userSession.getCurrentUrl().contains("answer")) {
            try {
                int index = rawData.indexOf("<div class=\"message ") + "<div class=\"message ".length();
            answerData = rawData.substring(index, rawData.indexOf("\"", index));
        } catch(Exception e) { answerData = ""; }
        }
    }
 //center valignwrapper selectanswer animated-background animated-background--fast
    //message  answerB
    public int getAnswer()
    {
        while(true) {
            if (answerData.length() > 0 && !answerData.contains("undefined") && !answerData.contains("correct")) {
                if (answerData.contains("A")) {
                    return 0;
                }
                else if (answerData.contains("B")) {
                    return 1;
                }
                else if (answerData.contains("C")) {
                    return 2;
                }
                else {
                    return 3;
                }
            }

        }
    }

    @Override
    public void run() {
        userSession = new FirefoxDriver();
        userSession.get("http://kahoot.it");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        WebElement pinbox = userSession.findElement(By.id("inputSession"));
        pinbox.sendKeys(Main.gameID + "\n");
        while(true)
            answerFetch();
    }
}
