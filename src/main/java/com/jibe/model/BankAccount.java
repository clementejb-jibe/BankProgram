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
    private final long accountNumber;
    private final int pin;
    private final User user;
    private double balance = 0;

    public BankAccount(long accountNumber, int pin, User user) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.user = user;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public User getUser() {
        return user;
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
        return getUser() + "{BankAccount | " + "accountNumber = " + accountNumber + ", balance = " + balance + '}';
    }


}
