package com.jibe.ui;

public class UserInterface {
    public static void userHomeInterface() {
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

    public static void registerUserInterface() {
        System.out.println("""
                +-------------------------------+
                |        REGISTER ACCOUNT       |
                +-------------------------------+""");
    }

    public static void loginUserInterface() {
        System.out.println("""
                +-------------------------------+
                |         LOGIN ACCOUNT         |
                +-------------------------------+""");
    }

    public static void findUserInterface() {
        System.out.println("""
                +-------------------------------+
                |          FIND ACCOUNT         |
                +-------------------------------+""");
    }

}
