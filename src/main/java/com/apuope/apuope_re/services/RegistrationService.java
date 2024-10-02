package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.entities.User;
import com.apuope.apuope_re.repositories.RegistrationRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    private final DSLContext dslContext;
    private final RegistrationRepository registrationRepository;

    public RegistrationService(DSLContext dslContext, RegistrationRepository registrationRepository) {
        this.dslContext = dslContext;
        this.registrationRepository = registrationRepository;
    }

    private ResponseData<String> validateRegistrationData(RegistrationData registrationData) {
        Optional<User> userByEmail =
                registrationRepository.findByEmail(registrationData.getEmail(), this.dslContext);

        if (userByEmail.isPresent()) {
            return new ResponseData<>(false, "User already exist for " +
                    "given" +
                    " email.");
        }

        Optional<User> userByUsername =
                registrationRepository.findByUsername(registrationData.getUsername(),
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

        return registrationRepository.createUser(registrationData, this.dslContext);
    }
}
