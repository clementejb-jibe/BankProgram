package com.jibe;

import com.jibe.app.BankApplication;
import com.jibe.controller.BankAccountController;
import com.jibe.controller.InputHandler;
import com.jibe.controller.UserController;
import com.jibe.controller.impl.BankAccountControllerInterface;
import com.jibe.controller.impl.UserControllerInterface;
import com.jibe.service.BankAccountService;
import com.jibe.service.UserService;
import com.jibe.ui.BankAccountInterface;
import com.jibe.ui.MenuUserInterface;
import com.jibe.ui.UserInterface;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // Clear console using ANSI escape code
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scan = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scan);

        BankAccountService bankService = new BankAccountService();
        UserService userService = new UserService();

        MenuUserInterface bankInterface = new BankAccountInterface();
        MenuUserInterface userMenuInterface = new UserInterface();
        BankAccountControllerInterface bankController = new BankAccountController(bankService, userService, inputHandler, bankInterface);
        UserControllerInterface userController = new UserController(userService, bankController, inputHandler, scan, userMenuInterface);

        BankApplication app = new BankApplication(userController);

        app.run();
    }
}