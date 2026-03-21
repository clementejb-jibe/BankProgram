
package com.jibe.entity;


import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Win11
 */
public class User {
    private final String fullName;
    private final String email;
    private final long userId;
    private final String passcode;
    private final List<BankAccount> bankAccounts = new LinkedList<>();

    public User(String fullName, String email, long userId, String passcode) {
        this.fullName = fullName;
        this.email = email;
        this.userId = userId;
        this.passcode = passcode;
    }

    //Getter
    public long getUserId() {
        return userId;
    }

    public String getPasscode() {
        return passcode;
    }

    //Get All Accounts
    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    //Add Bank Account
    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", userId=" + getUserId() +
                '}';
    }
}
