package com.example.myapplication1.model;


import java.io.Serializable;
import java.util.UUID;

public class Users implements Serializable {
    private String mUsername;
    private String mPassword;
    private UUID mId;

    public Users() {
        mId = UUID.randomUUID();
    }

    public Users(String username, String password) {
        mUsername = username;
        mPassword = password;
        mId = UUID.randomUUID();
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public UUID getId() {
        return mId;
    }
}