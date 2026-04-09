package com.jibe.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TransactionIdGenerator {
    private static final Random random = new Random();
    private static final Set<Long> transactionIds = new HashSet<>();

    public static long generate() {
        long id;

        do {
            id = 100000 + random.nextLong(900000);
        } while (transactionIds.contains(id));

        transactionIds.add(id);
        return id;
    }
}
