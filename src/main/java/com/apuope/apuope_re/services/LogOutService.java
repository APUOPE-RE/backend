package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
public class LogOutService {

    private final DSLContext dslContext;
    private final UserRepository userRepository;
    private final JWTService jwtService;



    public LogOutService(DSLContext dslContext, UserRepository userRepository, JWTService jwtService) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    public ResponseData<String> handleLogout(String token, HttpServletRequest request) {
        String userEmail = jwtService.extractEmail(token);
        var userOpt = userRepository.findByEmail(userEmail, dslContext);
        if (userOpt.isPresent()) {
            userRepository.logOutSession(userOpt.get().getId(), this.dslContext);
            return new ResponseData<>(true, "Logged out successfully.");
        }
        return new ResponseData<>(false, "User not found.");
    }

}
