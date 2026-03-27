
package com.jibe.app;

import com.jibe.controller.impl.UserControllerImp;

/**
 *
 * @author Win11
 */
public class BankApplication implements Runnable{
    private final UserControllerImp controller;
    
    public BankApplication(UserControllerImp controller) {
        this.controller = controller;
    }
    
    @Override
    public void run() {
        controller.homeMenu();
    }
}
