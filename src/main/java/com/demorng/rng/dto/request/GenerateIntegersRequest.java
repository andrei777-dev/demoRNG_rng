package com.demorng.rng.dto.request;

/**
 * Request body for POST /api/v1/rng/integers.
 * min/max are inclusive bounds; count is how many integers to generate.
 */
public record GenerateIntegersRequest(int min, int max, int count) {
}
