/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jibe.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Win11
 */
public class User {

    private final long userId;
    private final String passcode;
    private final List<BankAccount> bankAccounts = new ArrayList<>();

    public User(long userId, String passcode) {
        this.userId = userId;
        this.passcode = passcode;
    }

    //Getter
    public long getUserId() {
        return userId;
    }
    //Get Passcode
    public String getPasscode() {
        return passcode;
    }

    //Get All Accounts
    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    //Add Bank Account
    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
    }

    @Override
    public String toString() {
        return "User ID: " + getUserId();
    }

}
