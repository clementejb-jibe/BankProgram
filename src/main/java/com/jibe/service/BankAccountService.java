package com.jibe.service;


import com.jibe.exceptions.BankAccountDoNotExistsException;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.model.*;
import com.jibe.repository.BankAccountRepository;

/**
 *
 * @author Win11
 */
public class BankAccountService {

    private final BankAccountRepository bankRepo;
    //private long autoSetAccountNum = 10001; //Next Task is to add feature that one user can hold many accounts.


    public BankAccountService() {
        this.bankRepo = new BankAccountRepository();
    }

    public BankAccount findAccountNumber(long accountNumber) throws BankAccountDoNotExistsException {

        if (bankRepo.findAccountNumber(accountNumber) == null) {
            throw new BankAccountDoNotExistsException("Account do not exists!");
        }

        return bankRepo.findAccountNumber(accountNumber);
    }

    public void createBankAccountForUser(User user) {

        BankAccount newAccount = new BankAccount(user.getUserId(), user);
        
        bankRepo.save(newAccount.getAccountNumber(), newAccount);
    }

    public double getBalance(long accountNumber) {
        return bankRepo.getBalance(accountNumber);
    }

    public void deposit(long accountNumber, double amount) throws InvalidAmountException, BankAccountDoNotExistsException {

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must not be negative");
        }

        BankAccount account = findAccountNumber(accountNumber);
        
        account.deposit(amount);
    }

    public void withdraw(long accountNumber, double amount) throws InvalidAmountException, BankAccountDoNotExistsException {
        if (amount <= 0 ) {
            throw new InvalidAmountException("Amount must not be negative!");
        }

        if (amount > getBalance(accountNumber)) {
            throw new InvalidAmountException("Insufficient funds!");
        }

        BankAccount account = findAccountNumber(accountNumber);
        

        
        account.withdraw(amount);

    }

    public BankAccount getAccountInformation(long accountNumber) throws BankAccountDoNotExistsException {
        return findAccountNumber(accountNumber);



    }

}
