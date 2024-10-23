package com.Parking.GestionParking.email;

import com.Parking.GestionParking.entities.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service

public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendPasswordResetEmail(User user, String resetToken) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(user.getEmail());
            helper.setFrom(fromEmail);
            helper.setSubject("Password Reset");
            String resetLink = "http://localhost:4200/#/reset-password?token=" + resetToken;

            String emailContent = "Dear " + user.getUsername() + ",\n\n"
                    + "You have requested to reset your password. Please click the link below to reset your password:\n\n"
                    + resetLink + "\n\n"
                    + "If you didn't request this, you can ignore this email.";
            helper.setText(emailContent);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }
    @Override
    public void sendPasswordChangedEmail(User user) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(user.getEmail());
            helper.setFrom(fromEmail);
            helper.setSubject("Password Changed Confirmation");
            String emailContent = "Dear " + user.getUsername() + ",\n\n"
                    + "Your password has been successfully changed.\n\n"
                    + "If you didn't make this change, please contact us immediately.";
            helper.setText(emailContent);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }

    @Override
    public void sendFeedbackEmail(String toEmail, String feedbackDescription) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(toEmail);
            helper.setFrom(fromEmail);
            helper.setSubject("Feedback Submitted");

            String emailContent = "Dear User,\n\n"
                    + "Thank you for your feedback. We have received the following message:\n\n"
                    + feedbackDescription + "\n\n"
                    + "We appreciate your input!";
            helper.setText(emailContent);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }

}