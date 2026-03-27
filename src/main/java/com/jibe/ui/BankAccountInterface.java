package com.jibe.ui;

public class BankAccountInterface implements MenuUserImp {
    @Override
    public void showMenu() {
        System.out.println("""
                +-------------------------------+
                |          BANK ACCOUNT         |
                +-------------------------------+
                | 1. Register Bank Account      |
                | 2. Login Bank Account         |
                | 3. Search My Account          |
                | 4. View My Accounts           |
                | 5. Sign out                   |
                | 6. Exit                       |""");
    }

    public void showBankMenu() {
        System.out.println("""
                +-------------------------------+
                |              BANK             |
                +-------------------------------+
                | 1. Check Balance              |
                | 2. Deposit                    |
                | 3. Withdraw                   |
                | 4. Account Information        |
                | 5. Sign out                   |
                | 6. Exit                       |""");
    }

    @Override
    public void showRegisterInterface() {
        System.out.println("""
                +-------------------------------+
                |     REGISTER BANK ACCOUNT     |
                +-------------------------------+""");
    }

    public void showLoginInterface() {
        System.out.println("""
                +-------------------------------+
                |       LOG IN BANK ACCOUNT     |
                +-------------------------------+""");
    }

    public void showFindAccountInterface() {
        System.out.println("""
                +-------------------------------+
                |        FIND BANK ACCOUNT      |
                +-------------------------------+""");
    }


    public void showGetBalanceInterface() {
        System.out.println("""
                +-------------------------------+
                |      BANK ACCOUNT BALANCE     |
                +-------------------------------+""");
    }


    public void showDepositInterface() {
        System.out.println("""
                +-------------------------------+
                |            DEPOSIT            |
                +-------------------------------+""");
    }

    public void showWithdrawInterface() {
        System.out.println("""
                +-------------------------------+
                |            WITHDRAW           |
                +-------------------------------+""");
    }


    public void showGetAllAccountsInterface() {
        System.out.println("""
                +-------------------------------+
                |            ACCOUNTS           |
                +-------------------------------+""");
    }

    public void showLoggedInAccountInterface() {
        System.out.println("""
                +-------------------------------+
                |        ACCOUNTS DETAILS       |
                +-------------------------------+""");
    }
}
