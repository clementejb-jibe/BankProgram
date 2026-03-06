
package com.jibe.app;

import com.jibe.controller.UserController;

/**
 *
 * @author Win11
 */
public class BankApplication {
    private final UserController controller;
    
    public BankApplication(UserController controller) {
        this.controller = controller;
    }
    
    public void start() {
        controller.homeMenu();
    }
}
