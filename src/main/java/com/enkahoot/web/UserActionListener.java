package com.enkahoot.web;

import com.enkahoot.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ivan on 3/17/2016.
 * The main class to determine the main user's answers to copy
 */
public class UserActionListener implements Runnable{
    private WebDriver userSession;
    private String answerData = "";
    private BrowserType browser;
    public UserActionListener(BrowserType browser) //0 firefox 1 chrome 2 opera 3 IE
    {
        this.browser = browser;
        Thread userThread = new Thread(this);
        userThread.start();
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
                else if (answerData.contains("D")){
                    return 3;
                }
            }
        }
    }

    public void run() {
        switch(browser)
        {
            case FIREFOX:
                userSession = new FirefoxDriver();
                break;
            case CHROME:
                DesiredCapabilities chrome = DesiredCapabilities.chrome();
                chrome.setCapability(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "chromedriver" + (System.getProperty("os.name").contains("Windows") ? ".exe" : ""));
                System.setProperty("webdriver.chrome.driver", "chromedriver" + (System.getProperty("os.name").contains("Windows") ? ".exe" : ""));
                userSession = new ChromeDriver(chrome);
                break;
            case OPERA:
                userSession = new OperaDriver();
                break;
            case IE:
                userSession = new InternetExplorerDriver();
                break;
            case SAFARI:
                userSession = new SafariDriver();
        }
        Main.drivers.add(userSession);

        userSession.get("http://kahoot.it");
        WebDriverWait waiter = new WebDriverWait(userSession, 10);
        WebElement pinbox = waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputSession")));
        pinbox.sendKeys(Main.gameID + "\n");
        while(true)
            answerFetch();
    }
}
