package com.jibe.ui;

public class BankAccountInterface implements UI {
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
}
