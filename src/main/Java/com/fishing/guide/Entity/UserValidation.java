package com.fishing.guide.Entity;

public class UserValidation {

    private final String userGuid;
    private final boolean newUser;

    public UserValidation(String userGuid, boolean newUser) {
        this.userGuid = userGuid;
        this.newUser = newUser;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public boolean isNewUser() {
        return newUser;
    }
}
