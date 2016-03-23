package com.enkahoot.web;

import com.enkahoot.Main;
import com.enkahoot.User.KahootUser;
import com.enkahoot.User.UserType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

/**
 * Created by Ivan on 3/17/2016.
 */
public class KahootSession implements Runnable{
    //private HtmlUnitDriver headlessSession = new SilentHtmlUnitDriver();
    private PhantomJSDriver headlessSession;
    private KahootUser user;
    private UserType userType;
    private String gameID;
    private boolean answered = false;
    public KahootSession(KahootUser user, UserType answerType, String gameID)
    {
        userType = answerType;
        //settings.setPreference("startup.homepage_welcome_url.additional", "http://kahoot.it");
        this.user = user;
        //headlessSession.setJavascriptEnabled(true);
        this.gameID = gameID;
        Thread me = new Thread(this);
        me.start();
    }

    public void run() {
        DesiredCapabilities defs = DesiredCapabilities.phantomjs();
        if(System.getProperty("os.name").contains("Windows"))
            defs.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "phantomjs.exe");
        else
            defs.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "phantomjs");
        Main.drivers.add(headlessSession);

        headlessSession = new PhantomJSDriver(defs);
        headlessSession.get("http://kahoot.it");
        enterGame();
        waitForStart();
        answerMode();
    }
//inst to start to loop(getready to answer) gameover<asldfjaslf;j>
    public void enterGame()
    {
        WebElement pinbox = headlessSession.findElement(By.id("inputSession"));
        pinbox.sendKeys(gameID + "\n");

        while(!headlessSession.getCurrentUrl().contains("join"));

        WebElement nameBox = headlessSession.findElement(By.id("username"));
        nameBox.sendKeys(user.getUserName() + "\n");
    }

    public void waitForStart()
    {
        while(headlessSession.getCurrentUrl().contains("instructions"));
    }

    public void waitForAnswerTime()
    {
        if(headlessSession.getCurrentUrl().contains("getready"))
            answered = false;
        while(!headlessSession.getCurrentUrl().contains("answer"));
    }

    public boolean hasEnded()
    {
        return headlessSession.getCurrentUrl().contains("gameover");
    }

    public void answerMode() {
        while (!hasEnded()) {
            try {
                waitForAnswerTime();
                int choice;
                List<WebElement> elements = headlessSession.findElements(By.xpath("//button"));
                WebElement selected;
                switch (userType) {
                    case RANDOM:
                        choice = (int) (Math.random() * 4);
                        selected = elements.size() > 0 ? elements.get(choice) : null;
                        if (selected != null && selected.isDisplayed() && selected.isEnabled())
                            selected.click();
                        answered = true;
                        break;
                    case MIMIC:
                        choice = Main.mimicMaster.getAnswer();
                        selected = elements.size() > 0 ? elements.get(choice) : null;
                        if (selected != null && selected.isDisplayed() && selected.isEnabled()) {
                            selected.click();
                            answered = true;
                        }
                        break;
                }
            } catch(Exception e)
            {
            }
        }
    }
}
