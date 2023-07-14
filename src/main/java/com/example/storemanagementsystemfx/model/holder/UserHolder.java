package com.example.storemanagementsystemfx.model.holder;

import com.example.storemanagementsystemfx.model.User;

public class UserHolder {
    private User user;
    private final static UserHolder INSTANCE = new UserHolder();

    private UserHolder() {}

    public static UserHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public User getUser() {
        return this.user;
    }
}
