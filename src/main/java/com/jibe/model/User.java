/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jibe.model;

/**
 *
 * @author Win11
 */
public class User {

    private final long userId;
    private String name;
    private final String passcode;
    //private BankAccount account;

    public User(long userId, String passcode) {
        this.userId = userId;
        this.passcode = passcode;
    }

    //Getter
    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPasscode() {
        return passcode;
    }

    /*
    Setter
    
    public User setUserId(Long id) {
        this.userId = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setPasscode(String passcode) {
        this.passcode = passcode;
        return this;
    }

     */
    
    @Override
    public String toString() {
        return "User ID: " + getUserId() + "Passcode: " + getPasscode().hashCode();
    }

}
