/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jibe.controller;


import com.jibe.ui.MenuUserImp;
import com.jibe.controller.impl.BankAccountControllerImp;
import com.jibe.controller.impl.UserControllerImp;
import com.jibe.exceptions.*;
import com.jibe.entity.User;
import com.jibe.service.UserService;
import com.jibe.util.InputHandler;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Win11
 */
public class UserController implements UserControllerImp {

    private final UserService userService;
    private final BankAccountControllerImp bankController;
    private final InputHandler inputHandler;
    private final Map<Integer, Runnable> menus = new HashMap<>(); //Command pattern
    private final MenuUserImp userMenuInterface;



    public UserController(UserService service, BankAccountControllerImp bankController,
                          InputHandler inputHandle,  MenuUserImp userMenuInterface) {
        this.userService = service;
        this.bankController = bankController;
        this.inputHandler = inputHandle;
        this.userMenuInterface = userMenuInterface;

    }

    // List of Options in Main Menu
    private void commandList() {
        menus.put(1, this::register);
        menus.put(2, this::handleLogin);
        menus.put(3, this::find);
        menus.put(4, this::getAll);
    }

    /*
     *
     * Access Account Information (findUserById, getAll)
     *
     */

    //FindUserById
    public void find() {

        int searchAttempts = 3;

        while (searchAttempts > 0) {

            try {
                 userMenuInterface.showFindAccountInterface();

                var searchId = inputHandler.readLong("Enter User Id: ");

                System.out.println(userService.findUserById(searchId));
                return;

            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            }

            searchAttempts--;
            System.out.println("Attempts left: " + searchAttempts);

            if (searchAttempts == 0) {
                throw new SearchIdFailedExeption("No attempts left.");
            }
        }

    }

    public void getAll() {

        var registeredUsers = userService.getAll();

        if (registeredUsers.isEmpty()) {
            throw new NoRegisteredUserException("No registered users.");
        } else {
            registeredUsers.forEach(System.out::println);
        }
    }


    /*
     * Essential user interfaces that includes User Main Menus (registerUser, loginUser
     */

    //Register
    public void register() {

        while (true) {
            try {
                userMenuInterface.showRegisterInterface();

                var enteredFullName = inputHandler.readString("Enter Full Name: ");
                var enteredEmail = inputHandler.readString("Enter Email: ");
                var enteredPasscode = inputHandler.readString("Create Passcode: ");
                var passcodeConfirmation = inputHandler.readString("Confirm Passcode: ");

                var newUser = userService.registerUser(enteredFullName.toUpperCase(), enteredEmail, enteredPasscode, passcodeConfirmation);
                System.out.println("Account created successfully. Your user id is: " + newUser.getUserId());
                return;
            } catch (PasscodeNotMatchException e) {
                System.out.println(e.getMessage());

            }
        }


    }

    //Handle Login for command patter
    public void handleLogin()  {
        try {
            var loggedInUser = login();

            if (loggedInUser != null) {
                System.out.println("Log in Successful.");

                //BankAccount Menus
                bankController.homeMenu(loggedInUser);
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    //Login
    public User login() throws UserNotFoundException {
        var attempts = 3;

        while (attempts > 0) {

            try {
                userMenuInterface.showLoginInterface();

                var enteredUserId = inputHandler.readLong("Enter User ID: ");
                inputHandler.readStringLine();

                var enteredPasscode = inputHandler.readString("Enter Passcode: ");

                return userService.loginUser(enteredUserId, enteredPasscode);
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (InvalidPasscodeException e) {
                --attempts;
                System.out.println(e.getMessage() + " Attempts left: " + attempts);
            }
        }

        throw new LoginFailedException("Login failed, too many attempts!");
    }


    /*
     *
     * Main Menus of User (homeMenu)
     *
     */
    //Home Menu
    public void homeMenu() {

        commandList();

        var running = true;

        while (running) {
            try {

                userMenuInterface.showMenu();

                var selectOption = inputHandler.readInt("  Select Option: ");
                inputHandler.readStringLine();

                var commands = menus.get(selectOption);

                if (selectOption == 5) {
                    running = false;
                    continue;
                }

                if (commands != null) {
                    commands.run();
                } else {
                    throw new IllegalArgumentException("Option is not on the list!");
                }



            } catch (IllegalArgumentException | NoRegisteredUserException e) {
                System.out.println(e.getMessage());
            }
        }


    }

}
