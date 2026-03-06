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

        if (amount > getBalance(accountNumber)) {
            throw new InvalidAmountException("Amount must be greater than balance!");
        }

        BankAccount account = findAccountNumber(accountNumber);
        
        if (amount <= 0 ) {
             throw new InvalidAmountException("Amount must be greater than balance!");
        }
        
        account.withdraw(amount);

    }

    public BankAccount getAccountInformation(long accountNumber) throws BankAccountDoNotExistsException {
        BankAccount account = findAccountNumber(accountNumber);

        if (account != null) {
            return account;
        } else {
            throw new BankAccountDoNotExistsException("Account do not exists!");
        }

    }

}
