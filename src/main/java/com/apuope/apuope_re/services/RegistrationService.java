package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    private final DSLContext dslContext;
    private final UserRepository userRepository;

    public RegistrationService(DSLContext dslContext,
            UserRepository userRepository, EmailService emailService) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
    }

    private ResponseData<String> validateRegistrationData(RegistrationData registrationData) {
        Optional<UsersRecord> userByEmail =
                userRepository.findByEmail(registrationData.getEmail(), this.dslContext);

        if (userByEmail.isPresent()) {
            return new ResponseData<>(false, "User already exist for " +
                    "given" +
                    " email.");
        }

        Optional<UsersRecord> userByUsername =
                userRepository.findByUsername(registrationData.getUsername(),
                        this.dslContext);

        if (userByUsername.isPresent()) {
            return new ResponseData<>(false, "User already exist for " +
                    "given" +
                    " username.");
        }
        return new ResponseData<String>(true, "");
    }

    public ResponseData<String> registerUser(RegistrationData registrationData) {
        ResponseData<String> validatedData = this.validateRegistrationData(registrationData);

        if (!validatedData.getSuccess()){
            return validatedData;
        }

        return userRepository.createUser(registrationData, this.dslContext);
    }
}
