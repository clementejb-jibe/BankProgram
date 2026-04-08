
package com.jibe.controller;

import com.jibe.controller.impl.BankAccountControllerImp;
import com.jibe.exceptions.*;
import com.jibe.entity.BankAccount;
import com.jibe.entity.User;
import com.jibe.service.BankAccountService;
import com.jibe.service.UserService;
import com.jibe.ui.BankAccountUI;
import com.jibe.ui.impl.MenuUserImp;
import com.jibe.util.InputHandler;



/**
 *
 * @author Win11
 */
public class BankAccountController implements BankAccountControllerImp {

    private final BankAccountService bankService;
    private final UserService userService;
    private final InputHandler inputHandler;
    private final TransactionController transactionController;
    private final MenuUserImp bankInterface;
    //Constructor
    public BankAccountController(BankAccountService service,
                                 UserService userService,
                                 InputHandler inputHandler,
                                 MenuUserImp bankInterface,
                                 TransactionController transactionController) {
        this.bankService = service;
        this.userService = userService;
        this.inputHandler = inputHandler;
        this.bankInterface = bankInterface;
        this.transactionController = transactionController;
    }


    /**
     *
     * Bank Operators ( getBalance)
     *
     */

    //Get Balance
    public void getBalance(BankAccount loggedInAccount) throws BankAccountDoNotExistsException {
        var accountNumber = loggedInAccount.getAccountNumber();

        ((BankAccountUI) bankInterface).showGetBalanceInterface();

        System.out.println("Bank Account Balance: " + bankService.getBalance(accountNumber));
    }


    /**
     *
     * Access Account Information  (findAccountNumber, checkAccountInformation, getAllAccounts)
     *
     */

    //Find Account Number
    public void find(User loggedInUser) throws BankAccountDoNotExistsException {

        try {
            var accountNumber = inputHandler.readLong("Enter Account Number: ");

            bankInterface.showFindAccountInterface();

            var account = bankService.findAccountNumber(accountNumber);


            if (account.getUser().getUserId() != loggedInUser.getUserId()) {
                throw new UnauthorizedAccountAccessException("This account does not belong to the logged-in user.");
            }
            System.out.println(account);
        } catch (BankAccountDoNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    //Check the logged-in Bank Account information
    public void getAccountLoggedInInformation(BankAccount bankAccount) throws BankAccountDoNotExistsException {

        var accountNumber = bankAccount.getAccountNumber();

        ((BankAccountUI) bankInterface).showAccountInformationInterface();

        System.out.println(bankService.findAccountNumber(accountNumber));

       // System.out.println(bankService.findAccountNumber(accountNumber));
    }

    // Fetch the all registered accounts from logged-in User
    public void getAll(User loggedInUser) throws BankAccountDoNotExistsException {
        bankInterface.showGetAllAccountsInterface();
        userService.getAllBankAccounts(loggedInUser).forEach(System.out::println);
    }


    /**
     *
     * Essential user interfaces for Bank Main Menu (createBankAccount, loginBankAccount)
     *
     */

    //Create a user Bank Account
    public void register(User loggedInUser) {
        while (true) {
            try {

                bankInterface.showRegisterInterface();

                var pin = inputHandler.readInt("Create Pin: ");

                if (bankService.checkPin(pin)) {
                    var newAccount = bankService.createBankAccount(pin, loggedInUser);
                    System.out.println("Bank account created successfully. Your Bank account number is: " + newAccount.getAccountNumber());
                    return;
                }
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

                bankInterface.showLoginInterface();

                long accountNumber = inputHandler.readLong("Enter Account Number: ");

                int pin = inputHandler.readInt("Enter Pin: ");

                BankAccount account = bankService.findAccountNumber(accountNumber);


                if (account.getUser().getUserId() != loggedInUser.getUserId()) {
                    throw new UnauthorizedAccountAccessException("This account does not belong to the logged-in user.");
                }
                return bankService.login(accountNumber, pin);
            } catch (InvalidPinException e) {
                --attempts;
                System.out.println(e.getMessage() + "Attempts: " + attempts);
            } catch (BankAccountDoNotExistsException e) {
                System.out.println(e.getMessage());
            }
        }

        throw new LoginFailedException("Login failed, too many attempts!");
    }


    /**
     *
     * MainMenus of Bank Account user interface (bankAccountHomeMenus, bankMenus)
     *
     */

    //Bank Menu After User Login
    public void homeMenu(User loggedInUser) {

        while (true) {
            try {

                bankInterface.showMenu();

                var options = inputHandler.readInt("  Select Option: ");

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
                    default -> System.out.println("Option is not on the list.");
                }
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

                ((BankAccountUI) bankInterface).showBankMenu();

                int options = inputHandler.readInt("Select: ");

                switch (options) {
                    case 1 -> getBalance(loggedInAccount);
                    case 2 -> transactionController.handleDeposit(loggedInAccount);
                    case 3 -> transactionController.handleWithdraw(loggedInAccount);
                    case 4 -> transactionController.handleTransfer(loggedInAccount);
                    case 5 -> getAccountLoggedInInformation(loggedInAccount);
                    case 6 -> transactionController.getTransactionByAccountNumber(loggedInAccount);
                    case 7 -> isBankMainMenuRunning = false;
                    default -> System.out.println("Option is not on the selection, please try again!");

                }

            } catch (BankAccountDoNotExistsException | InvalidAmountException e) {
                System.out.println(e.getMessage());
            }

        }

    }

}
