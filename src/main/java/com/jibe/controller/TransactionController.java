package com.jibe.controller;

import com.jibe.entity.BankAccount;
import com.jibe.entity.TransactionType;
import com.jibe.exceptions.BankAccountDoNotExistsException;
import com.jibe.exceptions.EmptyTransactionException;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.service.BankAccountService;
import com.jibe.service.TransactionService;
import com.jibe.service.TransferService;
import com.jibe.ui.TransactionUI;
import com.jibe.util.InputHandler;

public class TransactionController {
    private final TransactionService transactionService;
    private final TransferService transferService;
    private final BankAccountService bankService;
    private final InputHandler inputHandler;
    private final TransactionUI transactionUI;

    public TransactionController(TransactionService transactionService, TransferService transferService, BankAccountService bankService, InputHandler inputHandler, TransactionUI transactionUI) {
        this.transactionService = transactionService;
        this.inputHandler = inputHandler;
        this.transactionUI = transactionUI;
        this.transferService = transferService;
        this.bankService = bankService;
    }

    public void handleDeposit(BankAccount bankAccount) throws InvalidAmountException {

        // Declaration of transaction type
        var type = TransactionType.DEPOSIT;

        while (true) {

            transactionUI.depositInterface();

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

            transactionUI.withdrawInterface();

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

    public void handleTransfer(BankAccount from){
        while (true) {
            try {


                var receiver = inputHandler.readLong("Enter Account Number to transfer: ");
                BankAccount to = bankService.findAccountNumber(receiver);

                var amount = inputHandler.readDouble("Enter amount: ");
                transferService.transfer(from, to, amount);
                return;
            } catch (InvalidAmountException | BankAccountDoNotExistsException e)  {
                System.out.println(e.getMessage());
            }
        }

    }

    public void getTransactionByAccountNumber(BankAccount bankAccount)  {

        try {
            var accountNumber = bankAccount.getAccountNumber();

            transactionService.findByAccountNumber(accountNumber).forEach(System.out::println);
        } catch (EmptyTransactionException e) {
            System.out.println(e.getMessage());
        }

    }
}
