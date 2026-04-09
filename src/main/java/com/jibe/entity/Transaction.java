package com.jibe.entity;


public record Transaction(TransactionType type, long transactionId, long accountNumber,  double amount) {

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", transactionId=" + transactionId +
                ", accountNumber=" + accountNumber +
                ", amount=" + amount +
                '}';
    }
}
