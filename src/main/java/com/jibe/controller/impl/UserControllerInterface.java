package com.jibe.controller.impl;

import com.jibe.exceptions.UserNotFoundException;
import com.jibe.entity.User;

public interface UserControllerInterface {

    void register();
    User login() throws UserNotFoundException;
    void find();
    void getAll();
    void homeMenu();
}
