
package com.jibe.controller;

import com.jibe.exceptions.BankAccountDoNotExistsException;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.exceptions.InvalidPinException;
import com.jibe.model.BankAccount;
import com.jibe.model.User;
import com.jibe.service.BankAccountService;
import com.jibe.service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Win11
 */
public class BankAccountController {

    private final BankAccountService bankService;
    private final Scanner scan;
    private final UserService userService;

    //Constructor
    public BankAccountController(BankAccountService service, UserService userService, Scanner scan) {
        this.bankService = service;
        this.scan = scan;
        this.userService = userService;
    }

    //Deposit Interface
    public void depositMenu(BankAccount loggedInAccount) {

        while (true) {
            try {
                long accNumber = loggedInAccount.getAccountNumber();

                System.out.print("Enter Amount: ");
                double amount = scan.nextDouble();

                bankService.deposit(accNumber, amount);

                System.out.print("Deposit successful! ");
                getBalance(loggedInAccount);
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

    public void withdraw(BankAccount loggedInAccount) {

        long accountNumber = loggedInAccount.getAccountNumber();
        while (true) {
            try {
                System.out.print("Enter Amount: ");
                double withdrawAmount = scan.nextDouble();

                bankService.withdraw(accountNumber, withdrawAmount);
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
    public void getBalance(BankAccount loggedInAccount) {
        long accountNumber = loggedInAccount.getAccountNumber();

        System.out.println("Bank Account Balance: " + bankService.getBalance(accountNumber));
    }

    //Check Account Information
    /*public void getAccount(User loggedInUser) throws BankAccountDoNotExistsException {

        System.out.print("Enter Account Number: ");
        long accountNumber = scan.nextLong();

        BankAccount account = bankService.getAccountInformation(accountNumber);

        if (account.getUser().getUserId() != loggedInUser.getUserId()) {
            System.out.println("This account does not belong to the logged-in user.");
            return;
        }

        System.out.println(account);
    }*/

    //Check Bank Account information that is logged-in
    public void checkAccountInformation(BankAccount bankAccount) throws BankAccountDoNotExistsException {

        long accountNumber = bankAccount.getAccountNumber();


        bankService.findAccountNumber(accountNumber);
    }

    public void getAllAccount(User loggedInUser) throws BankAccountDoNotExistsException {

        userService.getAllBankAccounts(loggedInUser).forEach(System.out::println);

    }

    //Create Bank Account UI
    public void createBankAccount(User loggedInUser) {
        while (true) {
            try {
                System.out.print("Create Pin: ");
                int pin = scan.nextInt();

                if (bankService.checkPin(pin)) {
                    bankService.createBankAccount(pin, loggedInUser);
                    System.out.println("Bank Account Created Successfully!");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");

            } catch (InvalidPinException e) {
                System.out.println(e.getMessage());

            }
        }
    }

    public BankAccount loginBankAccount(User loggedInUser) throws InvalidPinException, BankAccountDoNotExistsException {
        int attempts = 3;

       while (attempts > 0) {
           try {
               System.out.print("Enter Account Number: ");
               long accountNumber = scan.nextLong();

               System.out.print("Enter Pin: ");
               int pin = scan.nextInt();

               BankAccount account = bankService.login(accountNumber, pin);


               if (account.getUser().getUserId() != loggedInUser.getUserId()) {
                   System.out.println("Account do not belong to User!");
                   return null;
               }
                return account;
           } catch (InputMismatchException e) {
               System.out.println("Invalid input!");
           } catch (InvalidPinException | BankAccountDoNotExistsException e) {
               --attempts;
               System.out.println(e.getMessage() + "Attempts: " + attempts);
           }
       }

        System.out.println("Login failed, too many attempts!");
        return null;
    }


    //Bank Menu After User Login
    public void bankAccountHomeMenu(User loggedInUser) {

        while (true) {
            try {
                System.out.println("""
                        1. Create Bank Account
                        2. Login Bank Account
                        3. View My Accounts
                        4. Sign Out
                        5. Exit""");
                System.out.print("Select: ");
                var options = scan.nextInt();

                switch (options) {
                    case 1 -> createBankAccount(loggedInUser);
                    case 2 -> {
                        BankAccount loggedInAccount = loginBankAccount(loggedInUser);
                        if (loggedInAccount != null) bankMenu(loggedInAccount);
                    }
                    case 3 -> getAllAccount(loggedInUser);
                    case 4 -> {return;}
                    case 5 -> System.exit(0);

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scan.next();
            } catch (BankAccountDoNotExistsException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    // This is belonged to the bankAccountHomeMenu(User loggedInUser)
    // Bank Menu after logged in on Bank Account
    public void bankMenu(BankAccount loggedInAccount) {

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
                    case 1 -> getBalance(loggedInAccount);
                    case 2 -> depositMenu(loggedInAccount);
                    case 3 -> withdraw(loggedInAccount);
                    case 4 -> checkAccountInformation(loggedInAccount);
                    case 5 -> isBankMainMenuRunning = false;
                    case 6 -> System.exit(0);
                    default -> System.out.println("Option is not on the selection, please try again!");

                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again!");
                scan.next();
            } catch (BankAccountDoNotExistsException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
