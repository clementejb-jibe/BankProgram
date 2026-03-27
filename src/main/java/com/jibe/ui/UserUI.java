package com.jibe.ui;

public class UserUI implements MenuUserImp {
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

    public void showRegisterInterface() {
        System.out.println("""
                +-------------------------------+
                |        REGISTER ACCOUNT       |
                +-------------------------------+""");
    }

    public void showLoginInterface() {
        System.out.println("""
                +-------------------------------+
                |         LOGIN ACCOUNT         |
                +-------------------------------+""");
    }

    public void showFindAccountInterface() {
        System.out.println("""
                +-------------------------------+
                |          FIND ACCOUNT         |
                +-------------------------------+""");
    }

    public void showGetAllAccountsInterface() {
        System.out.println("""
                +-------------------------------+
                |            ACCOUNTS           |
                +-------------------------------+""");
    }

}
