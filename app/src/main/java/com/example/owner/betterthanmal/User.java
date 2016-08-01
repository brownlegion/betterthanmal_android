package com.example.owner.betterthanmal;

/**
 * Created by Krishna N. Deoram on 2016-07-28.
 * Gumi is love. Gumi is life.
 */

public class User {

    private static User instance;
    private String userId;

    private  User() {

    }

    public static User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
