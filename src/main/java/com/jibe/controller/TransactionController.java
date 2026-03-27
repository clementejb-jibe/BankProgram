package com.jibe.controller;

import com.jibe.entity.BankAccount;
import com.jibe.entity.TransactionType;
import com.jibe.exceptions.EmptyTransactionException;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.service.TransactionService;
import com.jibe.util.InputHandler;

public class TransactionController {
    private final TransactionService transactionService;
    private final InputHandler inputHandler;

    public TransactionController(TransactionService transactionService, InputHandler inputHandler) {
        this.transactionService = transactionService;
        this.inputHandler = inputHandler;
    }

    public void handleDeposit(BankAccount bankAccount) throws InvalidAmountException {

        // Declaration of transaction type
        var type = TransactionType.DEPOSIT;

        while (true) {
            try {
                var amount = inputHandler.readDouble("Enter amount to deposit: ");
                transactionService.process(type, bankAccount, amount);


                System.out.println("Deposit successful, new balance: " +  bankAccount.getBalance());
                return;
            } catch (InvalidAmountException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void handleWithdraw(BankAccount bankAccount) throws InvalidAmountException {

        var type = TransactionType.WITHDRAW;

        while (true) {
            try {
                var amount = inputHandler.readDouble("Enter amount to withdraw: ");
                transactionService.process(type, bankAccount, amount);

                System.out.println("Withdraw successful, new balance: " +  bankAccount.getBalance());
                return;
            } catch (InvalidAmountException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void getTransactionByAccountNumber(BankAccount bankAccount) throws InvalidAmountException {

        try {
            var accountNumber = bankAccount.getAccountNumber();

            transactionService.findByAccountNumber(accountNumber).forEach(System.out::println);
        } catch (EmptyTransactionException e) {
            System.out.println(e.getMessage());
        }

    }
}
