package com.example.myapplication1;

import java.io.Serializable;

public class Users implements Serializable {
    private String mUsername;
    private String mPassword;

    public Users() {

    }

    public Users(String username, String password) {
        mUsername = username;
        mPassword = password;
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
}