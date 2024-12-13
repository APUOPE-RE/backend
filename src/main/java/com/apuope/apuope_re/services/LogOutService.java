package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
public class LogOutService {

    private final DSLContext dslContext;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final UserCredentialsService userCredentialsService;

    public LogOutService(DSLContext dslContext, UserRepository userRepository, JWTService jwtService, UserCredentialsService userCredentialsService) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userCredentialsService = userCredentialsService;
    }


    public ResponseData<Object> handleLogout(String token) {
        try {
            String userEmail = jwtService.extractEmail(token);
            var response = userCredentialsService.checkAccountExists(userEmail);
            if (response.getSuccess()) {
                UsersRecord user = (UsersRecord) response.getData();
                userRepository.logOutSession(user.getId(), this.dslContext);
                return new ResponseData<>(true, "Logged out successfully.");
            }
            return response;
        } catch (Exception e){
            return new ResponseData<>(false, "Logging out failed. Please, try again.");
        }
    }

}
