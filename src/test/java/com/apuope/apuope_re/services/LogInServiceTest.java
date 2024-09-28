package com.apuope.apuope_re.services;

import com.apuope.apuope_re.entities.User;
import com.apuope.apuope_re.repositories.UserRepository;
import com.apuope.apuope_re.types.UserCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LogInServiceTest {
    @Mock
    private UserRepository userRepository;  // Mock the repository

    @InjectMocks
    private LogInService logInService;  // Inject mock repository into the service

    @BeforeEach
    void setUp() {
        // Initialize the mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        User mockUser = Mockito.mock(User.class);
        when(userRepository.findByEmailAndPasswordHash("test@success.com", "password123")).thenReturn(Optional.of(mockUser));

        UserCredentials userCredentials = new UserCredentials("test@success.com", "password123");
        boolean result = logInService.validateUser(userCredentials);
        assertTrue(result, "Login successful");
    }

    @Test
    void testLoginFailure() {
        User mockUser = new User();
        when(userRepository.findByEmailAndPasswordHash("test@failure.com", "password123")).thenReturn(Optional.empty());

        UserCredentials userCredentials = new UserCredentials("test@failure.com", "password123");
        boolean result = logInService.validateUser(userCredentials);
        assertFalse(result, "Login unsuccessful");
    }
}