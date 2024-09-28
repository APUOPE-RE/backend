package com.apuope.apuope_re.services;

import com.apuope.apuope_re.entities.User;
import com.apuope.apuope_re.repositories.UserRepository;
import com.apuope.apuope_re.types.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LogInService {
    @Autowired
    private UserRepository userRepository;

    public boolean ValidateUser(UserCredentials userCredentials) {
        Optional<User> userOpt = userRepository.findByEmail(userCredentials.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getPasswordHash().equals(userCredentials.getPassword());
        } else {
            System.out.println("No user found with email: " + userCredentials.email);
            return false;
        }
    }

}
