/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.jibe.exceptions;

/**
 *
 * @author Win11
 */
public class InvalidPasscodeException extends Exception {

    /**
     * Creates a new instance of <code>InvalidPasscodeException</code> without
     * detail message.
     */
    public InvalidPasscodeException() {
    }

    /**
     * Constructs an instance of <code>InvalidPasscodeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidPasscodeException(String msg) {
        super(msg);
    }
}
