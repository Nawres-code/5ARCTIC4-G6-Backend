package com.Parking.GestionParking.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final MeterRegistry meterRegistry;

    @Autowired
    public MyController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/my-endpoint")
    public String myEndpoint() {
        meterRegistry.counter("my_endpoint_hits").increment(); // Increment the counter for this endpoint
        return "Hello, World!";
    }
}
