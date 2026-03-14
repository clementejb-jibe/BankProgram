package com.jibe.ui;

public class UserInterface implements UI{
    public void showMenu() {
        System.out.println("""
                +-------------------------------+
                |          BANK SYSTEM          |
                +-------------------------------+
                | 1. Register Account           |
                | 2. Login                      |
                | 3. Search Account             |
                | 4. Get All Account (Testing)  |
                | 5. Exit                       |""");
    }

    public void registerUserInterface() {
        System.out.println("""
                +-------------------------------+
                |        REGISTER ACCOUNT       |
                +-------------------------------+""");
    }

    public void loginUserInterface() {
        System.out.println("""
                +-------------------------------+
                |         LOGIN ACCOUNT         |
                +-------------------------------+""");
    }

    public void findUserInterface() {
        System.out.println("""
                +-------------------------------+
                |          FIND ACCOUNT         |
                +-------------------------------+""");
    }

}
