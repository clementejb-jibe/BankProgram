package com.jibe;

import com.jibe.app.BankApplication;
import com.jibe.controller.BankAccountController;
import com.jibe.controller.InputHandler;
import com.jibe.controller.UserController;
import com.jibe.controller.impl.BankAccountControllerInterface;
import com.jibe.controller.impl.UserControllerInterface;
import com.jibe.service.BankAccountService;
import com.jibe.service.UserService;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scan);

        BankAccountService bankService = new BankAccountService();
        UserService userService = new UserService();

        BankAccountControllerInterface bankController = new BankAccountController(bankService, userService, inputHandler);
        UserControllerInterface userController = new UserController(userService, bankController, inputHandler, scan);

        BankApplication app = new BankApplication(userController);

        app.run();
    }
}