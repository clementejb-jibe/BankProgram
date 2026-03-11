
package com.jibe.app;

import com.jibe.controller.impl.UserControllerInterface;

/**
 *
 * @author Win11
 */
public class BankApplication {
    private final UserControllerInterface controller;
    
    public BankApplication(UserControllerInterface controller) {
        this.controller = controller;
    }
    
    public void start() {
        controller.homeMenu();
    }
}
