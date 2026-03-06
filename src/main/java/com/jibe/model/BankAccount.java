/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jibe.model;

/**
 *
 * @author Win11
 */
public class BankAccount {
    private long accountNumber;
    private User user;
    private double balance = 0;
    
    
    
    public BankAccount(long accountNumber,User user) {
        this.accountNumber = accountNumber;
        this.user = user;

//this.user = new User(accountNumber, user.getPasscode());
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
    
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "BankAccount{" + "accountNumber = " + accountNumber + ", user = " + user + ", balance = " + balance + '}';
    }
    
    
    
}
