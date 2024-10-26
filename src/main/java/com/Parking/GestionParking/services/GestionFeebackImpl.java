package com.Parking.GestionParking.services;

import com.Parking.GestionParking.email.EmailService;
import com.Parking.GestionParking.repository.IFeedbackRepository;
import com.Parking.GestionParking.entities.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionFeebackImpl implements IGestionFeedback {
    @Autowired
    IFeedbackRepository iFeedbackRepository;

    @Autowired
    private EmailService emailService; // Inject the EmailService

    @Override
    public Feedback addFeedback(Feedback feedback) {
        Feedback savedFeedback = iFeedbackRepository.save(feedback); // Save the feedback first
        // Send email to the provided email address
        emailService.sendFeedbackEmail(feedback.getEmail(), feedback.getDescription());
        return savedFeedback; // Return the saved feedback
    }



    @Override
    public List<Feedback> retrieveAllFeedback() {
        return (List<Feedback>) iFeedbackRepository.findAll();
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        return iFeedbackRepository.save(feedback);
    }

    @Override
    public Feedback retrieveFeedback(Integer idFeedback) {
        return iFeedbackRepository.findById(idFeedback).orElse(null);
    }
    public void deleteFeedback(Integer idFeedback) {
        iFeedbackRepository.deleteById(idFeedback);
    }


}
