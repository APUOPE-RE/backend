package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationService {
    private final DSLContext dslContext;
    private final UserRepository userRepository;
    private final PasswordHashService passwordHashService;
    private final UserCredentialsService userCredentialsService;

    public RegistrationService(DSLContext dslContext,
                               UserRepository userRepository,
                               PasswordHashService passwordHashService, UserCredentialsService userCredentialsService) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
        this.passwordHashService = passwordHashService;
        this.userCredentialsService = userCredentialsService;
    }

    private ResponseData<Object> validateRegistrationData(RegistrationData registrationData) {
        ResponseData<Object> responseEmail  = userCredentialsService.emailNotFound(registrationData.getEmail());
        if (!responseEmail.getSuccess()) {
            return responseEmail;
        }

        ResponseData<Object> responseUsername  = userCredentialsService.usernameNotFound(registrationData.getUsername());
        if (!responseUsername.getSuccess()) {
            return responseUsername;
        }
        return new ResponseData<>(true, "");
    }

    public ResponseData<Object> registerUser(RegistrationData registrationData) {
        ResponseData<Object> validatedData = this.validateRegistrationData(registrationData);
        if (!validatedData.getSuccess()){
            return validatedData;
        }

        registrationData.setPasswordHash(passwordHashService.hashPassword(registrationData.getPasswordHash()));
        return userRepository.createUser(registrationData, this.dslContext);
    }

    public ResponseData<Object> verifyUser(UUID uuid){
        boolean response = userRepository.alterUserVerify(uuid, dslContext);

        if (response){
            return new ResponseData<>(true, "User verified");
        }
        return new ResponseData<>(false, "User verification failed.");
    }
}
