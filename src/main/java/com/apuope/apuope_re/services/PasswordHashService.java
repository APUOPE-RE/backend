package com.apuope.apuope_re.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordHashService {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public PasswordHashService() {}

    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return encoder.matches(password, hashedPassword);
    }
}
