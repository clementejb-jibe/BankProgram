
package com.jibe.controller;

import com.jibe.controller.impl.BankAccountControllerInterface;
import com.jibe.exceptions.*;
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
public class BankAccountController implements BankAccountControllerInterface {

    private final BankAccountService bankService;
    private final Scanner scan;
    private final UserService userService;

    //Constructor
    public BankAccountController(BankAccountService service, UserService userService, Scanner scan) {
        this.bankService = service;
        this.scan = scan;
        this.userService = userService;
    }


    /*
     *
     * Bank Operators ( getBalance, deposit, withdraw)
     *
     */

    //Deposit
    public void deposit(BankAccount loggedInAccount) {

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

    //Withdraw
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


    /*
     *
     * Access Account Information  (findAccountNumber, checkAccountInformation, getAllAccounts)
     *
     */

    //Find Account Number
    public void find(User loggedInUser) throws BankAccountDoNotExistsException {

        System.out.print("Enter Account Number: ");
        var accountNumber = scan.nextLong();

        BankAccount account = bankService.findAccountNumber(accountNumber);


        if (account.getUser().getUserId() != loggedInUser.getUserId()) {
            throw new UnauthorizedAccountAccessException("This account does not belong to the logged-in user.");
        }
        System.out.println(account);
        //return account;
    }

    //Check the logged-in Bank Account information
    public void getAccountLoggedInInformation(BankAccount bankAccount) throws BankAccountDoNotExistsException {

        long accountNumber = bankAccount.getAccountNumber();

        System.out.println(bankService.findAccountNumber(accountNumber))

        ;
    }

    // Fetch the all registered accounts from logged-in User
    public void getAll(User loggedInUser) throws BankAccountDoNotExistsException {

        userService.getAllBankAccounts(loggedInUser).forEach(System.out::println);
    }


    /*
     *
     * Essential user interfaces for Bank Main Menu (createBankAccount, loginBankAccount)
     *
     */

    //Create a user Bank Account
    public void register(User loggedInUser) {
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

    //Login user Bank Account
    public BankAccount login(User loggedInUser) throws InvalidPinException, BankAccountDoNotExistsException {
        int attempts = 3;

        while (attempts > 0) {
            try {
                System.out.print("Enter Account Number: ");
                long accountNumber = scan.nextLong();

                System.out.print("Enter Pin: ");
                int pin = scan.nextInt();

                BankAccount account = bankService.findAccountNumber(accountNumber);


                if (account.getUser().getUserId() != loggedInUser.getUserId()) {
                    throw new UnauthorizedAccountAccessException("This account does not belong to the logged-in user.");
                }
                return bankService.login(accountNumber, pin);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scan.next();
            } catch (InvalidPinException e) {
                --attempts;
                System.out.println(e.getMessage() + "Attempts: " + attempts);
            } catch (BankAccountDoNotExistsException e) {
                System.out.println(e.getMessage());
            }
        }

        throw new LoginFailedException("Login failed, too many attempts!");
    }


    /*
     *
     * MainMenus of Bank Account user interface (bankAccountHomeMenus, bankMenus)
     *
     */

    //Bank Menu After User Login
    public void homeMenu(User loggedInUser) {

        while (true) {
            try {
                System.out.println("""
                        1. Create Bank Account
                        2. Login Bank Account
                        3. Search My Bank Account
                        4. View My Accounts
                        5. Sign Out
                        6. Exit""");
                System.out.print("Select: ");
                var options = scan.nextInt();

                switch (options) {
                    case 1 -> register(loggedInUser);
                    case 2 -> {
                        BankAccount loggedInAccount = login(loggedInUser);
                        if (loggedInAccount != null) bankMenus(loggedInAccount);
                    }
                    case 3 -> find(loggedInUser);
                    case 4 -> getAll(loggedInUser);
                    case 5 -> {
                        return;
                    }
                    case 6 -> System.exit(0);

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scan.next();
            } catch (BankAccountDoNotExistsException | UnauthorizedAccountAccessException | LoginFailedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // This is belonged to the bankAccountHomeMenu(User loggedInUser)
    // Bank Menu after logged in on Bank Account
    public void bankMenus(BankAccount loggedInAccount) {

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
                    case 2 -> deposit(loggedInAccount);
                    case 3 -> withdraw(loggedInAccount);
                    case 4 -> getAccountLoggedInInformation(loggedInAccount);
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
