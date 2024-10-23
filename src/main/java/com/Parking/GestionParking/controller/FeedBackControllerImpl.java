package com.Parking.GestionParking.controller;

import com.Parking.GestionParking.entities.Feedback;
import com.Parking.GestionParking.services.GestionFeebackImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/feedback")
public class FeedBackControllerImpl {
    @Autowired
    private GestionFeebackImpl gestionFeeback;

    @GetMapping("/getall")
    public List<Feedback> getAllFeedbacks() {
        return gestionFeeback.retrieveAllFeedback();
    }

    @PostMapping("/addFeedback")
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return gestionFeeback.addFeedback(feedback);
    }

    @GetMapping("/getFeedbackById/{idFeedback}")
    public Feedback getFeedbackById(@PathVariable("idFeedback") Integer idFeedback) {
        return gestionFeeback.retrieveFeedback(idFeedback);
    }

    @PutMapping("/updateFeedback")
    public Feedback updateFeedback(@RequestBody Feedback feedback) {
        return gestionFeeback.updateFeedback(feedback);
    }

    @DeleteMapping("/deleteFeedback/{idFeedback}")
    public ResponseEntity<Integer> deleteFeedback(@PathVariable("idFeedback") Integer idFeedback) {
        gestionFeeback.deleteFeedback(idFeedback);
        return ResponseEntity.ok().body(idFeedback);
    }
}
