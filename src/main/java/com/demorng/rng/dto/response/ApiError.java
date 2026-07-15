package com.demorng.rng.dto.response;

import java.time.Instant;

/**
 * Standard error body returned by the API (mirrors the Currency service shape).
 */
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
