package com.Parking.GestionParking.controller;


import com.Parking.GestionParking.repository.IPosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/postdashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class PosteDashboardController {

    private final IPosteRepository posteRepository;

    @Autowired
    public PosteDashboardController(IPosteRepository posteRepository) {
        this.posteRepository = posteRepository;
    }

    @GetMapping("/post-count")
    public ResponseEntity<Long> getPostCount() {
            long totalPostes = posteRepository.countPostes();
            return ResponseEntity.ok(totalPostes);
        }
    }

