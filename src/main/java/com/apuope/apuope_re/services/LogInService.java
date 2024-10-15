package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.UserRepository;
import com.apuope.apuope_re.dto.UserCredentials;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LogInService {
    private final DSLContext dslContext;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public LogInService(DSLContext dslContext, UserRepository userRepository) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    public ResponseData<String> validateUser(UserCredentials userCredentials) {
        Optional<UsersRecord> userOpt =
                userRepository.findByEmail(userCredentials.getEmail(), this.dslContext);

        if (userOpt.isPresent()) {
            if (passwordEncoder.matches(userCredentials.getPasswordHash(), userOpt.get().getPasswordHash())) {
                userRepository.addSession(userOpt.get().getId(), this.dslContext);
                return new ResponseData<>(true, jwtService.generateToken(userOpt.get().getEmail()));
            }

        }
            return new ResponseData<>(false, "Invalid credentials. Please try again.");
    }

}
