package com.jibe.service;

import com.jibe.entity.BankAccount;
import com.jibe.entity.Transaction;
import com.jibe.entity.TransactionType;
import com.jibe.exceptions.BankAccountDoNotExistsException;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.repository.TransactionRepository;

public class TransferService {
    private final TransactionRepository transactionRepository;

    public TransferService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

    }

    public void process(BankAccount from, BankAccount to, double amount) throws InvalidAmountException, BankAccountDoNotExistsException {


        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }

        if (from.getBalance() < amount) {
            throw new InvalidAmountException("Insufficient balance. Please try again.");
        }

        from.setBalance(from.getBalance() - amount);

        to.setBalance(to.getBalance() + amount);

        var senderTransaction = new Transaction(
                TransactionType.TRANSFER,
                from.getAccountNumber(),
                amount
        );

        var receiverTransaction = new Transaction(
                TransactionType.TRANSFER,
                to.getAccountNumber(),
                amount
        );

        transactionRepository.save(senderTransaction);
        transactionRepository.save(receiverTransaction);


    }

}
