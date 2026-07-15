package com.demorng.rng.service;

import com.demorng.rng.dto.request.GenerateDoublesRequest;
import com.demorng.rng.dto.request.GenerateIntegersRequest;
import com.demorng.rng.dto.response.GenerateDoublesResponse;
import com.demorng.rng.dto.response.GenerateIntegersResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RngService {

    private final SecureRandomGenerator generator;
    private final ChiSquareMonitor monitor;

    public RngService(SecureRandomGenerator generator, ChiSquareMonitor monitor) {
        this.generator = generator;
        this.monitor = monitor;
    }

    public GenerateIntegersResponse generateIntegers(GenerateIntegersRequest request) {
        if (request.count() <= 0) {
            throw new IllegalArgumentException("count must be > 0");
        }
        if (request.min() > request.max()) {
            throw new IllegalArgumentException("min must be <= max");
        }

        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < request.count(); i++) {
            int v = generator.nextInt(request.min(), request.max());
            monitor.record(v, request.min(), request.max());
            values.add(v);
        }

        return new GenerateIntegersResponse(values);
    }

    public GenerateDoublesResponse generateDoubles(GenerateDoublesRequest request) {
        if (request.count() <= 0) {
            throw new IllegalArgumentException("count must be > 0");
        }

        List<Double> values = new ArrayList<>();

        for (int i = 0; i < request.count(); i++) {
            values.add(generator.nextDouble());
        }

        return new GenerateDoublesResponse(values);
    }
}
