package com.jibe.service;


import com.jibe.exceptions.BankAccountDoNotExistsException;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.exceptions.InvalidPinException;
import com.jibe.entity.BankAccount;
import com.jibe.entity.User;
import com.jibe.repository.BankAccountRepository;

/**
 *
 * @author Win11
 */
public class BankAccountService {

    private final BankAccountRepository bankRepo;
    private long autoSetAccountNum = 10001;


    public BankAccountService() {
        this.bankRepo = new BankAccountRepository();
    }

    public BankAccount findAccountNumber(long accountNumber) throws BankAccountDoNotExistsException {
        return bankRepo.findAccountNumber(accountNumber)
                .stream()
                .filter(b -> b.getAccountNumber() == accountNumber)
                .findFirst()
                .orElseThrow(() -> new BankAccountDoNotExistsException("Bank account do not exists."));
    }

    public BankAccount createBankAccount(int pin, User user) {

        var newAccount = new BankAccount(autoSetAccountNum, pin, user);

        bankRepo.save(newAccount.getAccountNumber(), newAccount);
        user.addBankAccount(newAccount);
        autoSetAccountNum++;

        return newAccount;
    }

    public double getBalance(long accountNumber) throws BankAccountDoNotExistsException {

        var account = findAccountNumber(accountNumber);

        return account.getBalance();
    }

    public void deposit(long accountNumber, double amount) throws InvalidAmountException, BankAccountDoNotExistsException {

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must not be negative");
        }

        var account = findAccountNumber(accountNumber);

        account.deposit(amount);
    }

    public void withdraw(long accountNumber, double amount) throws InvalidAmountException, BankAccountDoNotExistsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must not be negative!");
        }

        if (amount > getBalance(accountNumber)) {
            throw new InvalidAmountException("Insufficient funds!");
        }

        var account = findAccountNumber(accountNumber);


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
        var accNum = findAccountNumber(accountNumber);

        if (accNum != null &&  accNum.getPin() == pin) {
            return accNum;
        }  else {
            throw new InvalidPinException("Incorrect PIN!");
        }
    }


}
