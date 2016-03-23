package com.enkahoot.web;

/**
 * Created by Ivan on 3/19/2016.
 */
public enum BrowserType {
    FIREFOX(0), CHROME(1), OPERA(2), IE(3), SAFARI(4);
    public int browserValue;
    BrowserType(int i)
    {
        browserValue = i;
    }
}
