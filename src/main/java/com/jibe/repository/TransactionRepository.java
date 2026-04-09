package com.jibe.repository;

import com.jibe.entity.Transaction;
import com.jibe.repository.impl.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository implements Repository<Transaction, Long> {
    private final Map<Long, Transaction> transactions = new HashMap<>();

    @Override
    public void save(Transaction transaction, Long transactionId) {
        this.transactions.put(transaction.transactionId(), transaction);
    }

    // Get All transactions of single bank account
    public List<Transaction> findByAccountNumber(long accountNumber) {
        return transactions.values()
                .stream()
                .filter(t -> t.accountNumber() == accountNumber)
                .toList();
    }


}
