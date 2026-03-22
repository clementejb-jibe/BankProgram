package com.jibe.service;

import com.jibe.exceptions.BankAccountDoNotExistsException;
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



    public BankAccountService(BankAccountRepository bankRepo) {
        this.bankRepo = bankRepo;

    }

    public BankAccount findAccountNumber(long accountNumber) throws BankAccountDoNotExistsException {
        return bankRepo.findAccountNumber(accountNumber)
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
