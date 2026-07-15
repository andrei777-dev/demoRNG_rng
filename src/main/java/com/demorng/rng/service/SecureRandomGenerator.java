package com.demorng.rng.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class SecureRandomGenerator {

    private final SecureRandom secureRandom = new SecureRandom();

    public int nextInt(int min, int max) {
        int range = max - min + 1;
        return min + secureRandom.nextInt(range);
    }

    /** Returns a secure random double in [0, 1). Caller scales it if needed. */
    public double nextDouble() {
        return secureRandom.nextDouble();
    }
}
