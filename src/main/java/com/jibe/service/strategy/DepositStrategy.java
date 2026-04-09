package com.jibe.service.strategy;

import com.jibe.entity.BankAccount;
import com.jibe.entity.Transaction;
import com.jibe.entity.TransactionType;
import com.jibe.exceptions.InvalidAmountException;
import com.jibe.repository.TransactionRepository;
import com.jibe.service.impl.TransactionStrategy;
import com.jibe.util.TransactionIdGenerator;


/**
 * A concrete implementation of {@link TransactionStrategy} that handles deposit transactions.
 *
 * <p>When executed, this strategy adds the specified amount to the bank account's
 * current balance and records the transaction in the repository.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *   TransactionStrategy deposit = new DepositStrategy(transactionRepository);
 *   deposit.process(myAccount, 1000.00);
 * </pre>
 */

public class DepositStrategy implements TransactionStrategy {

    private final TransactionRepository transactionRepository;

    public DepositStrategy(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void process(BankAccount bankAccount, double amount) throws InvalidAmountException {

        var transactionId = TransactionIdGenerator.generate();

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }

        bankAccount.setBalance(bankAccount.getBalance() + amount);

        transactionRepository.save(
                new Transaction(TransactionType.DEPOSIT,
                        transactionId,
                        bankAccount.getAccountNumber(),
                        amount),
                transactionId);
    }
}
