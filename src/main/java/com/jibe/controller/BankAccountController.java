/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jibe.controller;

import com.jibe.exceptions.BankAccountDoNotExistsException;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.model.User;
import com.jibe.service.BankAccountService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Win11
 */
public class BankAccountController {

    private final BankAccountService bankService;
    private final Scanner scan;

    public BankAccountController(BankAccountService service, Scanner scan) {
        this.bankService = service;
        this.scan = scan;
    }

    //Deposit Interface
    public void depositMenu(User loggedInUser) {

        while (true) {
            try {
                long accNumber = loggedInUser.getUserId();

                System.out.print("Enter Amount: ");
                double amount = scan.nextDouble();

                bankService.deposit(accNumber, amount);

                System.out.print("Deposit successful! ");
                getBalance(loggedInUser);
                break;
            } catch (InvalidAmountException | BankAccountDoNotExistsException e) {
                System.out.println(e.getMessage());
                scan.next();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scan.next();
            }

        }
    }

    public void withdraw(User loggedInUser) {

        long accNumber = loggedInUser.getUserId();
        while (true) {
            try {
                System.out.print("Enter Amount: ");
                double withdrawAmount = scan.nextDouble();

                bankService.withdraw(accNumber, withdrawAmount);
                break;
            } catch (InvalidAmountException | BankAccountDoNotExistsException e) {
                System.out.println(e.getMessage());
                //scan.next();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scan.next();
            }
        }
    }

    //Get Balance
    public void getBalance(User loggedInUser) {
        long accountNumber = loggedInUser.getUserId();

        System.out.println("Bank Account Balance: " + bankService.getBalance(accountNumber));
    }

    //Check Account Information
    public void getAccount(User loggedInUser) throws BankAccountDoNotExistsException {

        long accountNumber = loggedInUser.getUserId();

        System.out.println(bankService.getAccountInformation(accountNumber));


    }

    //


    // Bank Main Menu after login
    public void bankMainMenu(User loggedInUser) {

        var isBankMainMenuRunning = true;

        while (isBankMainMenuRunning) {
            try {
                System.out.println("""
                        1. Check Balance
                        2. Deposit
                        3. Withdraw
                        4. Check Account Information
                        5. Sign Out
                        6. Exit""");
                System.out.print("Select: ");

                int options = scan.nextInt();

                switch (options) {
                    case 1 -> getBalance(loggedInUser);
                    case 2 -> depositMenu(loggedInUser);
                    case 3 -> withdraw(loggedInUser);
                    case 4 -> getAccount(loggedInUser);
                    case 5 -> isBankMainMenuRunning = false;
                    case 6 -> System.exit(0);
                    default -> System.out.println("Option is not on the selection, please try again!");

                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again!");
                scan.next();
            } catch (BankAccountDoNotExistsException e) {
                System.out.println(e.getMessage());
                scan.next();
            }

        }

    }

}
