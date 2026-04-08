package com.jibe.service.strategy;

import com.jibe.entity.BankAccount;
import com.jibe.entity.Transaction;
import com.jibe.entity.TransactionType;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.repository.TransactionRepository;
import com.jibe.service.impl.TransactionStrategy;

/**
 * A concrete implementation of {@link TransactionStrategy} that handles withdrawal transactions.
 *
 * <p>When executed, this strategy deducts the specified amount from the bank account's
 * current balance and records the transaction in the repository.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *   TransactionStrategy withdrawal = new WithdrawalStrategy(transactionRepository);
 *   withdrawal.process(myAccount, 500.00);
 * </pre>
 */

public class WithdrawStrategy implements TransactionStrategy {

    private final TransactionRepository transactionRepository;

    public WithdrawStrategy(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void process(BankAccount bankAccount, double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }

        if (bankAccount.getBalance() < amount) {
            throw new InvalidAmountException("Balance is insufficient");
        }

        bankAccount.setBalance(bankAccount.getBalance() - amount);

        transactionRepository.save(
                new Transaction(
                        TransactionType.WITHDRAW,
                    bankAccount.getAccountNumber(),
                    amount
        ));
    }
}
