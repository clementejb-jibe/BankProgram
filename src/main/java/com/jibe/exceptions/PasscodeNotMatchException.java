/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.jibe.exceptions;

/**
 *
 * @author Win11
 */
public class PasscodeNotMatchException extends Exception {

    /**
     * Creates a new instance of <code>PasscodeNotMatchException</code> without
     * detail message.
     */
    public PasscodeNotMatchException() {
    }

    /**
     * Constructs an instance of <code>PasscodeNotMatchException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PasscodeNotMatchException(String msg) {
        super(msg);
    }
}
