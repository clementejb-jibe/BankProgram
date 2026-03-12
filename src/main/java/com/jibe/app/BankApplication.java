
package com.jibe.app;

import com.jibe.controller.impl.UserControllerInterface;

/**
 *
 * @author Win11
 */
public class BankApplication implements Runnable{
    private final UserControllerInterface controller;
    
    public BankApplication(UserControllerInterface controller) {
        this.controller = controller;
    }
    
    @Override
    public void run() {
        controller.homeMenu();
    }
}
