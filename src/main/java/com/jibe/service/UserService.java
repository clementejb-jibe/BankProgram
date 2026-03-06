package com.jibe.service;


import com.jibe.exceptions.InvalidPasscodeException;
import com.jibe.exceptions.PasscodeNotMatchException;
import com.jibe.exceptions.UserNotFoundException;
import com.jibe.model.User;
import com.jibe.repository.UserRepository;

import java.util.Map;

/**
 *
 * @author Win11
 */
public class UserService {
    private final BankAccountService bankService;
    private final UserRepository users;
    long autoSetId = 1001;

    public UserService(BankAccountService service) {
        this.users = new UserRepository();
        this.bankService = service;
    }

    
    //Registration
   
    public void registerUser(String passcode, String passcodeConfirmation) throws PasscodeNotMatchException {

        if (!passcode.equals(passcodeConfirmation)) {
            throw new PasscodeNotMatchException("Passcode not match!");
        }
        
        User newUser = new User(autoSetId, passcode);

        users.save(autoSetId, newUser);
        
        bankService.createBankAccountForUser(newUser);
        
        autoSetId++;

    }

    //Login
    
    public User loginUser(long userId, String passcode) throws InvalidPasscodeException, UserNotFoundException {

        User user = findUserById(userId);

        if (user != null && user.getPasscode().equals(passcode)) {
            return user;
        } else {
            throw new InvalidPasscodeException("Incorrect Passcode!");
        }
    }

    
   
    public Map<Long, User> getAll(){
        return users.getAll();
    }

    
    public User findUserById(long id) throws UserNotFoundException {

        return users.findUserById(id)
                .orElseThrow(() -> 
                        new UserNotFoundException("User not found!")
                );
    }

}
