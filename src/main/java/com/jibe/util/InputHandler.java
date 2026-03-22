package com.jibe.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scan;


    public InputHandler(Scanner scan) {
        this.scan = scan;
    }

    // Read integer numbers
    public int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again!");
                scan.next();
            }
        }
    }

    // Read double numbers
    public double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scan.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again!");
                scan.next();
            }
        }
    }

    // Read long numbers
    public long readLong(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scan.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again!");
                scan.next();
            }
        }
    }

    // Read Strings
    public String readString(String message) {
        System.out.print(message);
        return scan.nextLine();
    }

    public void readStringLine() {
        scan.nextLine();
    }
}
