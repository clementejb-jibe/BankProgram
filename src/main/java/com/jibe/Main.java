package com.jibe;

import com.jibe.app.BankApplication;
import com.jibe.controller.BankAccountController;
import com.jibe.controller.UserController;
import com.jibe.service.BankAccountService;
import com.jibe.service.UserService;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BankAccountService bankService = new BankAccountService();
        BankAccountController bankController = new BankAccountController(bankService, scan);
        UserService userService = new UserService(bankService);
        UserController userController = new UserController(userService, bankController);
        BankApplication app = new BankApplication(userController);

        app.start();
    }
}