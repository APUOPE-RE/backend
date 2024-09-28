package com.apuope.apuope_re.services;

import com.apuope.apuope_re.entities.User;
import com.apuope.apuope_re.repositories.UserRepository;
import com.apuope.apuope_re.dto.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LogInService {
    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(UserCredentials userCredentials) {
        Optional<User> userOpt =
                userRepository.findByEmailAndPasswordHash(userCredentials.getEmail(),
                        userCredentials.getPasswordHash());

        if (userOpt.isPresent()) {
            return true;
        } else {
            System.out.println("No user found with email: " + userCredentials.email);
            return false;
        }
    }

}
