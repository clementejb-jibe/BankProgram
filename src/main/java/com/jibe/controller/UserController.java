/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jibe.controller;


import com.jibe.exceptions.InvalidPasscodeException;
import com.jibe.exceptions.PasscodeNotMatchException;
import com.jibe.exceptions.UserNotFoundException;
import com.jibe.model.User;
import com.jibe.service.UserService;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Win11
 */
public class UserController {

    private final UserService userService;
    private final Scanner scan;
    private final BankAccountController bankController;

    public UserController(UserService service, BankAccountController bankController, Scanner scan) {
        this.userService = service;
        this.bankController = bankController;
        this.scan = scan;
    }

    //Menu methods use for homeMenu
    //Register
    private void registerMenu() {

        while (true) {
            try {
                System.out.println("REGISTER ACCOUNT");

                System.out.print("Create Passcode: ");
                var enteredPasscode = scan.nextLine();

                System.out.print("Confirm Passcode: ");
                var passcodeConfirmation = scan.nextLine();

                userService.registerUser(enteredPasscode, passcodeConfirmation);
                System.out.println("Account Created Successfully!");
                return;
            } catch (PasscodeNotMatchException e) {
                System.out.println(e.getMessage());

            }
        }


    }

    //Login
    private User loginAccount() throws UserNotFoundException {

        var attempts = 3;
        while (attempts > 0) {

            try {
                System.out.print("Enter User ID: ");
                var enteredUserId = scan.nextInt();
                scan.nextLine();

                System.out.print("Enter Passcode: ");
                var enteredPasscode = scan.nextLine();

                return userService.loginUser(enteredUserId, enteredPasscode);
            } catch (InvalidPasscodeException e) {
                --attempts;
                System.out.println(e.getMessage() + " Attempts left: " + attempts);

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again!");
                scan.next();
            }
        }


        System.out.println("Login failed, too many attempts!");
        return null;
    }

    //FindUserById
    public void findUserById() {

        int searchAttempts = 3;

        while (searchAttempts > 0) {

            try {
                System.out.print("Enter User Id: ");
                var searchId = scan.nextLong();

                System.out.println(userService.findUserById(searchId));
                return;

            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
                scan.next();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again!");
                scan.next();
            }

            searchAttempts--;
            System.out.println("Attempts left: " + searchAttempts);
            //scan.next();

            if (searchAttempts == 0) {
                System.out.println("No attempts left!");
            }
        }

    }

    public void getAll() {

        Map<Long, User> registeredUsers = userService.getAll();

        if (registeredUsers.isEmpty()) {
            System.out.println("No Registered User!");
        } else {
            registeredUsers.forEach((_, user) -> System.out.println(user));
        }
    }

    /*







     */
    //Home Menu
    public void homeMenu() {

        var homeMenuRunning = true;

        while (homeMenuRunning) {
            try {
                System.out.println("""
                        HOME MENU
                        1. Register Account
                        2. Login
                        3. Exit
                        4. Find Account
                        5. Check Account (For Debugging)""");
                System.out.print("Select: ");

                var selectOption = scan.nextInt();

                scan.nextLine();

                switch (selectOption) {
                    case 1 -> registerMenu(); //Register Account

                    case 2 -> {
                        User loggedinUser = loginAccount();

                        if (loggedinUser != null) {
                            System.out.println("Successful Login!");
                            //BankController menus
                            bankController.bankAccountHomeMenu(loggedinUser);
                        }

                    }
                    case 3 -> homeMenuRunning = false;

                    case 4 -> findUserById();
                    case 5 -> getAll();

                    default -> System.out.println("Option is not on the selection, please try again!");

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again!");
                scan.next();
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }


    }

}
