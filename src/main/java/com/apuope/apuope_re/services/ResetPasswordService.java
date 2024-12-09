package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResetPasswordData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.dto.TokenData;
import com.apuope.apuope_re.jooq.tables.records.TokenRecord;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.TokenRepository;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResetPasswordService {
    private final ZoneId TIMEZONE = ZoneId.of("Europe/Helsinki");
    private final DSLContext dslContext;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public ResetPasswordService(DSLContext dslContext,
            UserRepository userRepository, TokenRepository tokenRepository) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

   public ResponseData<Object> generateEmailToken(String email){
       try {
           UsersRecord user = userRepository.findByEmail(email, dslContext);

            if (user != null) {
                TokenData tokenData = new TokenData(user.getId(), UUID.randomUUID());
                ResponseData<String> response = tokenRepository.createToken(tokenData, dslContext);

                if (response.getSuccess()) {
                    return new ResponseData<>(true, tokenRepository.findByAccountId(user.getId(), dslContext));
                }
                return new ResponseData<>(false, "");
            }
            return new ResponseData<>(false, "");
        } catch (Exception e) {
           return new ResponseData<>(false, "");
        }
    }

    public ResponseData<Object> resetPassword(ResetPasswordData resetPasswordData) {
        try {
            TokenRecord token = tokenRepository.findByUuid(resetPasswordData.getUuid(), dslContext);

            if (LocalDateTime.now(TIMEZONE).isBefore(token.getExpirationTime())) {
                if (!token.getValid()) {
                    return new ResponseData<>(false, "This reset password request has already been completed. Request a new link if needed.");
                }
                String hashedPassword = PasswordHashService.hashPassword(resetPasswordData.getPassword());
                boolean success = userRepository.alterUserResetPassword(token.getAccountId(), hashedPassword, dslContext);

                if (success) {
                    tokenRepository.invalidateToken(token.getId(), dslContext);
                    return new ResponseData<>(true, "Password reset successfully.");
                }
                // This should be never reached, but response is good to be here just for sure.
                return new ResponseData<>(false, "No rows affected.");
            }
            return new ResponseData<>(false, "Reset password link is expired.");
        } catch (Exception e){
            return new ResponseData<>(false, "Resetting password failed. Please, try again.");
        }
    }
}
