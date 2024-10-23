package com.Parking.GestionParking.email;

import com.Parking.GestionParking.entities.User;
import com.Parking.GestionParking.repository.UserRepository;
import com.Parking.GestionParking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@RestController
@RequestMapping("/reset-password")
public class ResetPasswordController {



@Autowired
private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping
    public ResponseEntity<?> resetPassword(@RequestParam String email, @RequestParam String newPassword,@RequestParam String confirmPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        String encodedPassword = passwordEncoder.encode(newPassword);
        // Update user's password
        user.setPassword(encodedPassword);
        userRepository.save(user);

        // Send confirmation email
        emailService.sendPasswordChangedEmail(user);

        return ResponseEntity.ok().build();
    }
}