package com.Parking.GestionParking.controller;

import com.Parking.GestionParking.entities.Reclamation;
import com.Parking.GestionParking.repository.IReclamationRepository;
import com.Parking.GestionParking.repository.ISubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reclamationdashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class ReclamationDashboardController {

    private final IReclamationRepository reclamationRepository;

    @Autowired
    public ReclamationDashboardController(IReclamationRepository reclamationRepository) {
        this.reclamationRepository = reclamationRepository;
    }

    @GetMapping("/reclamation-count")
    public ResponseEntity<Long> getReclamationCount() {
        long totalReclamations = reclamationRepository.countReclamation();

        return ResponseEntity.ok(totalReclamations);
    }


    @GetMapping("/pending-reclamations-count")
    public ResponseEntity<Long> getPendingReclamationCount() {
        long pendingCount = reclamationRepository.countPendingReclamations();
        return ResponseEntity.ok(pendingCount);
    }
    @GetMapping("/processing-reclamations-count")
    public ResponseEntity<Long> getProcessingCount() {
        long processingCount = reclamationRepository.countProcessingReclamations();
        return ResponseEntity.ok(processingCount);
    }

    @GetMapping("/accepted-reclamations-count")
    public ResponseEntity<Long> getAcceptedCount() {
        long acceptedCount = reclamationRepository.countAcceptedReclamations();
        return ResponseEntity.ok(acceptedCount);
    }


    @GetMapping("/reclamation-percentage-pending")
    public ResponseEntity<Double> getPendingReclamationPercentage() {
        long totalReclamation = reclamationRepository.countReclamation();
        long pendingRec = reclamationRepository.countPendingReclamations();

        double pendingPercentage = (double) pendingRec / totalReclamation * 100;

        return ResponseEntity.ok(pendingPercentage);
    }

    @GetMapping("/reclamation-percentage-processing")
    public ResponseEntity<Double> getProcessingReclamationPercentage() {
        long totalReclamation = reclamationRepository.countReclamation();
        long processingRec = reclamationRepository.countProcessingReclamations();

        double processingPercentage = (double) processingRec / totalReclamation * 100;

        return ResponseEntity.ok(processingPercentage);
    }

    @GetMapping("/reclamation-percentage-accepted")
    public ResponseEntity<Double> getAccesptedReclamationPercentage() {
        long totalReclamation = reclamationRepository.countReclamation();
        long accesptedRec = reclamationRepository.countAcceptedReclamations();

        double accesptedPercentage = (double) accesptedRec / totalReclamation * 100;

        return ResponseEntity.ok(accesptedPercentage);
    }

}