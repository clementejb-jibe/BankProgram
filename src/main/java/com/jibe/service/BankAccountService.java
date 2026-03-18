package com.jibe.service;


import com.jibe.exceptions.BankAccountDoNotExistsException;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.exceptions.InvalidPinException;
import com.jibe.model.BankAccount;
import com.jibe.model.User;
import com.jibe.repository.BankAccountRepository;

/**
 *
 * @author Win11
 */
public class BankAccountService {

    private final BankAccountRepository bankRepo;
    private long autoSetAccountNum = 10001; //Next Task is to add feature that one user can hold many accounts.


    public BankAccountService() {
        this.bankRepo = new BankAccountRepository();
    }

    public BankAccount findAccountNumber(long accountNumber) throws BankAccountDoNotExistsException {

        if (bankRepo.findAccountNumber(accountNumber) == null) {
            throw new BankAccountDoNotExistsException("Account do not exists!");
        }

        return bankRepo.findAccountNumber(accountNumber);
    }

    public BankAccount createBankAccount(int pin, User user) {

        BankAccount newAccount = new BankAccount(autoSetAccountNum, pin, user);

        bankRepo.save(newAccount.getAccountNumber(), newAccount);
        user.addBankAccount(newAccount);
        autoSetAccountNum++;

        return newAccount;
    }

    public double getBalance(long accountNumber) {

        var account = bankRepo.findAccountNumber(accountNumber);

        return account.getBalance();
    }

    public void deposit(long accountNumber, double amount) throws InvalidAmountException, BankAccountDoNotExistsException {

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must not be negative");
        }

        BankAccount account = findAccountNumber(accountNumber);

        account.deposit(amount);
    }

    public void withdraw(long accountNumber, double amount) throws InvalidAmountException, BankAccountDoNotExistsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must not be negative!");
        }

        if (amount > getBalance(accountNumber)) {
            throw new InvalidAmountException("Insufficient funds!");
        }

        BankAccount account = findAccountNumber(accountNumber);


        account.withdraw(amount);

    }


    //Check Pin if valid
    public boolean checkPin(int pin) {

        if (pin >= 1000 && pin <= 9999) {
            return true;
        } else {
            throw new InvalidPinException("Invalid PIN. Please enter a 4-digit PIN (1000–9999)");
        }

    }

    //Login
    public BankAccount login(long accountNumber, int pin) throws InvalidPinException, BankAccountDoNotExistsException {
        BankAccount accNum = findAccountNumber(accountNumber);

        if (accNum != null &&  accNum.getPin() == pin) {
            return accNum;
        }  else {
            throw new InvalidPinException("Incorrect PIN!");
        }
    }


}
