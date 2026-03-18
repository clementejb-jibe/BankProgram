/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jibe.repository;

import com.jibe.model.BankAccount;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Win11
 */
public class BankAccountRepository {
    
    private final Map<Long, BankAccount> accounts = new HashMap<>();
    
    public void save(long accountNumber, BankAccount acc) {        
        accounts.put(accountNumber, acc);
    }
   
    /*public double getBalance(long accountNumber) {
        
        BankAccount account = findAccountNumber(accountNumber);
        
        return account.getBalance();
    }*/
    
    public BankAccount findAccountNumber(long accountNumber){
        return accounts.get(accountNumber);
    }
}
