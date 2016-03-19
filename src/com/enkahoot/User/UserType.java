package com.enkahoot.User;

/**
 * Created by Ivan on 3/17/2016.
 */
public enum UserType {
    MIMIC("m"), RANDOM("r"), SMART("s"), DEAD("d");
    public String type;
    UserType(String type)
    {
        this.type = type;
    }
}
