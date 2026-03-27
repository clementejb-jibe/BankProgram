package com.jibe.controller.impl;

import com.jibe.exceptions.BankAccountDoNotExistsException;
import com.jibe.exceptions.InvalidPinException;
import com.jibe.entity.BankAccount;
import com.jibe.entity.User;

public interface BankAccountControllerImp {
    void register(User loggedInUser);
    BankAccount login(User loggedInUser) throws InvalidPinException, BankAccountDoNotExistsException;
    void find(User user) throws BankAccountDoNotExistsException;
    void getAccountLoggedInInformation(BankAccount bankAccount) throws BankAccountDoNotExistsException;
    void getAll(User loggedInUser) throws BankAccountDoNotExistsException;
    void homeMenu(User loggedInUser);
}
