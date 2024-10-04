package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerificationService {
    private final DSLContext dslContext;
    private final UserRepository userRepository;

    public VerificationService(DSLContext dslContext, UserRepository userRepository, EmailService emailService) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
    }

    public ResponseData<String> verifyUser(UUID uuid){
        boolean response = userRepository.alterUser(uuid, dslContext);

        if (response){
            return new ResponseData<>(true, "User verified");
        }
        return new ResponseData<>(false, "User verification failed");
    }
}