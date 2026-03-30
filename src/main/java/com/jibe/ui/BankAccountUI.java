package com.jibe.ui;

public class BankAccountUI implements MenuUserImp {
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
                | 4. Transfer                   |
                | 5. Account Information        |
                | 6. Transaction History        |
                | 7. Sign out                   |""");
    }

    @Override
    public void showRegisterInterface() {
        System.out.println("""
                +-------------------------------+
                |     REGISTER BANK ACCOUNT     |
                +-------------------------------+""");
    }

    @Override
    public void showLoginInterface() {
        System.out.println("""
                +-------------------------------+
                |       LOG IN BANK ACCOUNT     |
                +-------------------------------+""");
    }

    @Override
    public void showFindAccountInterface() {
        System.out.println("""
                +-------------------------------+
                |        FIND BANK ACCOUNT      |
                +-------------------------------+""");
    }

    @Override
    public void showGetAllAccountsInterface() {
        System.out.println("""
                +-------------------------------+
                |            ACCOUNTS           |
                +-------------------------------+""");
    }

    public void showAccountInformationInterface() {
        System.out.println("""
                +-------------------------------+
                |        ACCOUNTS DETAILS       |
                +-------------------------------+""");
    }

    public void showGetBalanceInterface() {
        System.out.println("""
                +-------------------------------+
                |      BANK ACCOUNT BALANCE     |
                +-------------------------------+""");
    }
}
