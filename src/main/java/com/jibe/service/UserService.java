package com.jibe.service;


import com.jibe.exceptions.BankAccountDoNotExistsException;
import com.jibe.exceptions.InvalidPasscodeException;
import com.jibe.exceptions.PasscodeNotMatchException;
import com.jibe.exceptions.UserNotFoundException;
import com.jibe.entity.BankAccount;
import com.jibe.entity.User;
import com.jibe.repository.UserRepository;
import com.jibe.util.SecurityUtil;

import java.util.*;

/**
 *
 * @author Win11
 */
public class UserService {

    private final UserRepository users;
    private long autoSetId = 1001;
    private final SecurityUtil securityUtil;

    public UserService(UserRepository userRepository, SecurityUtil securityUtil) {
        this.users = userRepository;
        this.securityUtil = securityUtil;

    }


    //Registration
    public User registerUser(String fullName, String email, String passcode, String passcodeConfirmation) throws PasscodeNotMatchException {

        if (!passcode.equals(passcodeConfirmation)) throw new PasscodeNotMatchException("Passcode not match!");

        var hiddenPasscode = securityUtil.hashPasscode(passcode);


        var newUser = new User(fullName, email, autoSetId, hiddenPasscode);

        users.save(autoSetId, newUser);

        autoSetId++;

        return newUser;
    }

    //Login
    public User loginUser(long userId, String passcode) throws InvalidPasscodeException, UserNotFoundException {
        var user = findUserById(userId);

        var hiddenPasscode = securityUtil.hashPasscode(passcode);

        if (user.getPasscode().equals(hiddenPasscode))
            return user;
        else
            throw new InvalidPasscodeException("Passcode is invalid!");
    }

    //Fetch All Bank Accounts that is belonged to the User
    public List<BankAccount> getAllBankAccounts(User user) throws BankAccountDoNotExistsException {

        if (user.getBankAccounts().isEmpty())
            throw new BankAccountDoNotExistsException("No registered bank accounts found!");

        return new LinkedList<>(user.getBankAccounts());
    }

    public List<User> getAll() {
        return users.getAll();
    }


    public User findUserById(long id) throws UserNotFoundException {


         return users.findUserById(id)
                 .orElseThrow(() -> new UserNotFoundException("User not found!"));

    }

    //Data Validation (Email)
    public boolean isEmailExists(String email) {

        for (var u : users.getAll()) {
            if (u.getEmail().equals(email)) {
            return true;
            }
        }
        return false;
    }


}
