package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService {
    private final UserRepository userRepository;
    private final DSLContext dslContext;

    public UserCredentialsService(UserRepository userRepository, DSLContext dslContext) {
        this.userRepository = userRepository;
        this.dslContext = dslContext;
    }

    public ResponseData<Object> checkAccountExists(String email) {
        UsersRecord user = userRepository.findVerifiedUserByEmail(email, this.dslContext);
        if (user != null){
            return new ResponseData<>(true, user);
        }
        return new ResponseData<>(false, "Invalid credentials. Please, try again.");
    }

    public ResponseData<Object> checkUnverifiedAccountExists(String email) {
        UsersRecord user = userRepository.findByEmail(email, this.dslContext);
        if (user != null){
            return new ResponseData<>(true, user);
        }
        return new ResponseData<>(false, "Invalid credentials. Please, try again.");
    }

    public ResponseData<Object> emailNotFound(String email) {
        UsersRecord user = userRepository.findByEmail(email, this.dslContext);
        if (user != null){
            return new ResponseData<>(false, "Account already exists for given email.");
        }
        return new ResponseData<>(true, "");
    }

    public ResponseData<Object> usernameNotFound(String username) {
        UsersRecord user = userRepository.findByUsername(username, this.dslContext);
        if (user != null){
            return new ResponseData<>(false, "Account already exists for given username.");
        }
        return new ResponseData<>(true, "");
    }

    public boolean checkValidPassword(String passwordHash, String dbPasswordHash){
        return PasswordHashService.checkPassword(passwordHash, dbPasswordHash);
    }
}
