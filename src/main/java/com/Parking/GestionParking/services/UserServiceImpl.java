package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.User;
import com.Parking.GestionParking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Optional<User> findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        // Implement your logic here to create a password reset token for the user
        // This could involve generating a token, storing it in the database, etc.
        // For demonstration purposes, let's just print the user and token
        System.out.println("User: " + user.getUsername() + ", Token: " + token);
    }
}