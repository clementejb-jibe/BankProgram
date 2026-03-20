
package com.jibe.repository;

import com.jibe.entity.BankAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Win11
 */
public class BankAccountRepository {
    
    private final Map<Long, BankAccount> accounts = new HashMap<>();
    
    public void save(long accountNumber, BankAccount acc) {        
        accounts.put(accountNumber, acc);
    }
    
    public Optional<BankAccount> findAccountNumber(long accountNumber){
        return Optional.ofNullable(accounts.get(accountNumber));
    }
}
