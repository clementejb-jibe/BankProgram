package com.jibe.service;

import com.jibe.entity.BankAccount;
import com.jibe.entity.Transaction;
import com.jibe.entity.TransactionType;
import com.jibe.exceptions.EmptyTransactionException;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.repository.TransactionRepository;
import com.jibe.service.impl.TransactionStrategy;
import com.jibe.service.strategy.DepositStrategy;
import com.jibe.service.strategy.WithdrawStrategy;

import java.util.List;

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    // This method find the bank account by account number then fetch the all transaction of the bank account.
    public List<Transaction> findByAccountNumber(long accountNumber) {
        var transacts = transactionRepository.findByAccountNumber(accountNumber);
        if (transacts.isEmpty()) {
            throw new EmptyTransactionException("Empty transaction");
        }
        return transacts;
    }

    /**
     * Handles the Transaction Type by using Strategy Pattern.
     * The strategy will handle the behavior of each type of transaction.
     * If transaction type is DEPOSIT or WITHDRAW, it will create an object that implements TransactionStrategy
     */

    public void process(TransactionType type, BankAccount bankAccount, double amount) throws InvalidAmountException {
        TransactionStrategy strategy;

        switch (type) {
            case DEPOSIT -> {
                strategy = new DepositStrategy(transactionRepository);
                strategy.process(bankAccount, amount);
            }
            case WITHDRAW -> {
                strategy = new WithdrawStrategy(transactionRepository);
                strategy.process(bankAccount, amount);
            }
        }

    }
}
