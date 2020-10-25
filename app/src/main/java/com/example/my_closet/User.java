package com.example.my_closet;

import java.io.Serializable;

public class User implements Serializable {
    private String userID;
    private String userPW;
    private String userName;

    public User(){
        userName="";
        userPW="";
        userID="";
    }

    public User(String userID, String userPW, String userName)
    {
        this.userID = userID;
        this.userPW = userPW;
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
