package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.Feedback;

import java.util.List;

public interface IGestionFeedback {
    Feedback addFeedback(Feedback feedback);
    List<Feedback> retrieveAllFeedback();
    Feedback updateFeedback(Feedback feedback);
    Feedback retrieveFeedback (Integer idFeedback);
    void deleteFeedback(Integer idFeedback);


}
