package com.Parking.GestionParking.auth;

import com.Parking.GestionParking.entities.Token;
import com.Parking.GestionParking.entities.User;
import com.Parking.GestionParking.repository.TokenRepository;
import com.Parking.GestionParking.repository.UserRepository;
import com.Parking.GestionParking.security.JwtService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(1) // Assurez-vous que l'ID est de type Integer
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .password("password")
                .role("Admin") // Assurez-vous que le rôle est défini comme prévu
                .build();
    }

    @Test
    void testRegister() throws MessagingException {
        RegistrationRequest request = new RegistrationRequest("John", "Doe", "john.doe@example.com", "password");

        when(userRepository.save(any(User.class))).thenReturn(user);
        // Ajoutez vos assertions ici pour valider le comportement de l'enregistrement
    }
}
