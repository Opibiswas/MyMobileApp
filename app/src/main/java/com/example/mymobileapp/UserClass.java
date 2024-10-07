package com.example.mymobileapp;

public class UserClass {

    public UserClass() {

    }

    public String username;
    public String email;
    public String password;
    public String divDis;
    public String bDate;

    public UserClass(String username, String divDis, String bDate, String email, String password) {

        this.username = username;
        this.divDis = divDis;
        this.bDate = bDate;
        this.email = email;
        this.password = password;
    }

}
