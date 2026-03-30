package com.jibe.repository;

import com.jibe.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();


    public void save(Transaction transaction) {
        this.transactions.add(transaction);
    }

    // Get transactions
    public List<Transaction> findByAccountNumber(long accountNumber) {
        return transactions.stream()
                .filter(t -> t.accountNumber() == accountNumber)
                .toList();
    }


}
