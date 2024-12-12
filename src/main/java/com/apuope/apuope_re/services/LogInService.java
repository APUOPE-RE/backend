package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.UserRepository;
import com.apuope.apuope_re.dto.UserCredentials;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInService {
    private final DSLContext dslContext;
    private final UserRepository userRepository;
    private final UserCredentialsService userCredentialsService;

    @Autowired
    private JWTService jwtService;

    public LogInService(DSLContext dslContext, UserRepository userRepository, UserCredentialsService userCredentialsService) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
        this.userCredentialsService = userCredentialsService;
    }

    public ResponseData<Object> validateUser(UserCredentials userCredentials) {
        try {
            var response = userCredentialsService.checkAccountExists(userCredentials.getEmail());
            if (response.getSuccess()) {
                UsersRecord user = (UsersRecord) response.getData();
                if (userCredentialsService.checkValidPassword(userCredentials.getPasswordHash(), user.getPasswordHash())) {
                    userRepository.addSession(user.getId(), this.dslContext);
                    return new ResponseData<>(true, jwtService.generateToken(user.getEmail()));
                } else {
                    return new ResponseData<>(false, "Logging in failed. Please, try again.");
                }
            }
            return response;
        } catch (Exception e){
            return new ResponseData<>(false, "Logging in failed. Please, try again.");
        }
    }
}
