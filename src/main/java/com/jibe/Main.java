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


        var scan = new Scanner(System.in);
        var inputHandler = new InputHandler(scan);

        var bankService = new BankAccountService();
        var userService = new UserService();

        //UI
        MenuUserInterface bankInterface = new BankAccountInterface();
        MenuUserInterface userMenuInterface = new UserInterface();

        //Controllers
        BankAccountControllerInterface bankController = new BankAccountController(bankService, userService, inputHandler, bankInterface);
        UserControllerInterface userController = new UserController(userService, bankController, inputHandler, scan, userMenuInterface);

        var app = new BankApplication(userController);

        app.run();
    }
}