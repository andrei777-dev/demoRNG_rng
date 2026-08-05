package com.demorng.rng.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Secure source of random numbers, backed by {@link SecureRandom}.
 * Self-seeded from OS entropy (no manual seed), so values are unpredictable.
 */
@Component
public class SecureRandomGenerator {

    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * Returns one sere random integer in tje inclusive range [min, max].
     *
     * @param min inclusive lower bound
     * @param max inclusive upper bound
     * @return a random integer between min and max (both included)
     */
    public int nextInt(int min, int max) {
        int range = max - min + 1;
        return min + secureRandom.nextInt(range);
    }

    /** Returns a secure random double in [0, 1). Caller scales it if needed. */
    public double nextDouble() {
        return secureRandom.nextDouble();
    }
}
