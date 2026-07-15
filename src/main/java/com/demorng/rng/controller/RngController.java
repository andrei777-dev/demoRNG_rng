package com.demorng.rng.controller;

import com.demorng.rng.dto.response.GenerateDoublesResponse;
import com.demorng.rng.dto.request.GenerateDoublesRequest;
import com.demorng.rng.dto.request.GenerateIntegersRequest;
import com.demorng.rng.dto.response.GenerateIntegersResponse;
import com.demorng.rng.service.RngService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST entry point for the RNG service.
 * (gRPC version comes later — kept in pom/proto for the future migration.)
 */
@RestController
@RequestMapping("/api/v1/rng")
public class RngController {

    private RngService rngService;

    public RngController(RngService rngService) {
        this.rngService = rngService;
    }

    @PostMapping("/integers")
    public GenerateIntegersResponse generateIntegers(@RequestBody GenerateIntegersRequest request) {
        return rngService.generateIntegers(request);
    }

    @PostMapping("/doubles")
    public GenerateDoublesResponse generateDoubles(@RequestBody GenerateDoublesRequest request) {
        return rngService.generateDoubles(request);
    }

}
