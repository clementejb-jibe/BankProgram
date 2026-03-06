/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.jibe.exceptions;

/**
 *
 * @author Win11
 */
public class InvalidAmountException extends Exception {

    /**
     * Creates a new instance of <code>InvalidAmountException</code> without
     * detail message.
     */
    public InvalidAmountException() {
    }

    /**
     * Constructs an instance of <code>InvalidAmountException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidAmountException(String msg) {
        super(msg);
    }
}
