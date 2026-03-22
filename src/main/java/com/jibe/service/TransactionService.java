package com.jibe.service;

import com.jibe.entity.BankAccount;
import com.jibe.entity.Transaction;
import com.jibe.entity.TransactionType;
import com.jibe.exceptions.EmptyTransactionException;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.repository.TransactionRepository;

import java.util.List;

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> findByAccountNumber(long accountNumber) {
        var transacts = transactionRepository.findByAccountNumber(accountNumber);
        if (transacts.isEmpty()) {
            throw new EmptyTransactionException("Empty transaction");
        }
        return transacts;
    }

    public void deposit(BankAccount bankAccount, double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }

        bankAccount.setBalance(bankAccount.getBalance() + amount);

        var transact = new Transaction(
                TransactionType.DEPOSIT,
                bankAccount.getAccountNumber(),
                amount
        );
        save(transact);


    }

    public void withdraw(BankAccount bankAccount, double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }

        if (bankAccount.getBalance() < amount) {
            throw new InvalidAmountException("Balance is insufficient");
        }

        bankAccount.setBalance(bankAccount.getBalance() - amount);

        var transact = new Transaction(
                TransactionType.WITHDRAW,
                bankAccount.getAccountNumber(),
                amount
        );
        save(transact);
    }
}
