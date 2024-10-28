package com.Parking.GestionParking.email;

import com.Parking.GestionParking.entities.User;

public interface EmailService {
    void sendPasswordResetEmail(User user, String resetToken);
    public void sendPasswordChangedEmail(User user);
    void sendFeedbackEmail(String toEmail, String feedbackDescription);


}