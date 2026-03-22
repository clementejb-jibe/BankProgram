package com.jibe.entity;


public record Transaction(TransactionType type, long accountNumber,  double amount) {

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", accountNumber=" + accountNumber +
                ", amount=" + amount +
                '}';
    }
}
