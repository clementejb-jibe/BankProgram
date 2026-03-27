package com.jibe.service;

import com.jibe.entity.BankAccount;
import com.jibe.entity.Transaction;
import com.jibe.exceptions.InvalidAmountException;

/**
 * Defines a strategy for processing financial transactions on a bank account.
 *
 * <p>Implement this interface to create different types of transaction behaviors
 * (e.g., deposit, withdrawal, transfer) without changing the core account logic.
 * This follows the <b>Strategy Design Pattern</b>.</p>
 */

public interface TransactionStrategy {

    void process(BankAccount bankAccount, double amount) throws InvalidAmountException;
}
