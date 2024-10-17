package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.UserRepository;
import com.apuope.apuope_re.dto.UserCredentials;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LogInService {
    private final DSLContext dslContext;
    private final UserRepository userRepository;

    public LogInService(DSLContext dslContext, UserRepository userRepository) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
    }

    public ResponseData<String> validateUser(UserCredentials userCredentials) {
        Optional<UsersRecord> userOpt =
                userRepository.findVerifiedUserByEmail(userCredentials.getEmail(), this.dslContext);

        if (userOpt.isPresent()) {
            boolean credentialsValid = PasswordHashService.checkPassword(userCredentials.getPasswordHash(), userOpt.get().getPasswordHash());
            if (credentialsValid) {
                userRepository.addSession(userOpt.get().getId(), this.dslContext);
                return new ResponseData<>(true, "");
            }
        }
        return new ResponseData<>(false, "Invalid credentials. Please try again.");
    }
}
