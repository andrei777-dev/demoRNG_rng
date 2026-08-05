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
 * Expose secure random number generation to internal callers(the game engines like scratch-engine).
 * Delegates all logic to {@link RngService}
 */
@RestController
@RequestMapping("/api/v1/rng")
public class RngController {

    private RngService rngService;

    public RngController(RngService rngService) {
        this.rngService = rngService;
    }

    /**
     * Generates a list of secure random integrations within an inclusive range.
     *
     * @param request the range bounds and how many integers to generate (min, max, count)
     * @return the generated integers wrapped in a response object
     */
    @PostMapping("/integers")
    public GenerateIntegersResponse generateIntegers(@RequestBody GenerateIntegersRequest request) {
        return rngService.generateIntegers(request);
    }

    /**
     * Generates a list of secure random doubles, each in the range [0, 1).
     *
     * @param request how many doubles to generate (count)
     * @return the generated doubles wrapped in a response object
     */
    @PostMapping("/doubles")
    public GenerateDoublesResponse generateDoubles(@RequestBody GenerateDoublesRequest request) {
        return rngService.generateDoubles(request);
    }

}
