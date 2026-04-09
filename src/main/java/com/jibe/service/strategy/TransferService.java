package com.jibe.service.strategy;

import com.jibe.entity.BankAccount;
import com.jibe.entity.Transaction;
import com.jibe.entity.TransactionType;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.repository.TransactionRepository;
import com.jibe.util.TransactionIdGenerator;

public class TransferService {
    private final TransactionRepository transactionRepository;

    public TransferService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

    }

    public void transfer(BankAccount from, BankAccount to, double amount) throws InvalidAmountException {

        var transactionId = TransactionIdGenerator.generate();

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }

        if (from.getBalance() < amount) {
            throw new InvalidAmountException("Insufficient balance. Please try again.");
        }

        from.setBalance(from.getBalance() - amount);

        to.setBalance(to.getBalance() + amount);


        //Save the Sender's transaction
        transactionRepository.save(
                new Transaction(
                        TransactionType.TRANSFER,
                        transactionId,
                        from.getAccountNumber(),
                        amount),
                transactionId);

        //Save the Receiver's transaction
        transactionRepository.save(
                new Transaction(
                    TransactionType.TRANSFER,
                    transactionId,
                    to.getAccountNumber(),
                    amount),
                transactionId);


    }

}
