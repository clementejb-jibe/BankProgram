package com.jibe;

import com.jibe.app.BankApplication;
import com.jibe.controller.BankAccountController;
import com.jibe.controller.TransactionController;
import com.jibe.repository.BankAccountRepository;
import com.jibe.repository.TransactionRepository;
import com.jibe.repository.UserRepository;
import com.jibe.service.TransactionService;
import com.jibe.util.InputHandler;
import com.jibe.controller.UserController;
import com.jibe.controller.impl.BankAccountControllerInterface;
import com.jibe.controller.impl.UserControllerInterface;
import com.jibe.service.BankAccountService;
import com.jibe.service.UserService;
import com.jibe.ui.BankAccountInterface;
import com.jibe.ui.MenuUserInterface;
import com.jibe.ui.UserInterface;
import com.jibe.util.SecurityUtil;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        //Repositories
        var bankAccountRepository = new BankAccountRepository();
        var userRepository = new UserRepository();
        var transactionRepository = new TransactionRepository();

        //utils
        var securityUtil = new SecurityUtil();
        var scan = new Scanner(System.in);
        var inputHandler = new InputHandler(scan);

        var transactionService = new TransactionService(transactionRepository);
        var bankService = new BankAccountService(bankAccountRepository);
        var userService = new UserService(userRepository, securityUtil);


        //UI
        MenuUserInterface bankInterface = new BankAccountInterface();
        MenuUserInterface userMenuInterface = new UserInterface();

        //Controllers
        TransactionController transactionController = new TransactionController(transactionService, inputHandler);
        BankAccountControllerInterface bankController = new BankAccountController(bankService, userService, inputHandler, bankInterface, transactionController);
        UserControllerInterface userController = new UserController(userService, bankController, inputHandler, userMenuInterface);

        var app = new BankApplication(userController);

        app.run();
    }
}