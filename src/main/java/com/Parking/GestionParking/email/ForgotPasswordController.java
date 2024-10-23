package com.Parking.GestionParking.email;

import com.Parking.GestionParking.entities.User;
import com.Parking.GestionParking.repository.UserRepository;
import com.Parking.GestionParking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/forgot-password")

public class ForgotPasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        String resetToken = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, resetToken);
        emailService.sendPasswordResetEmail(user, resetToken);
        return ResponseEntity.ok().build();
    }
}