package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.dto.TokenData;
import com.apuope.apuope_re.jooq.tables.records.TokenRecord;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.repositories.TokenRepository;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResetPasswordService {
    private final DSLContext dslContext;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public ResetPasswordService(DSLContext dslContext,
            UserRepository userRepository, TokenRepository tokenRepository) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

   public Result<TokenRecord> generateEmailToken(Integer accountId){
        try {
            TokenData tokenData = new TokenData(accountId, UUID.randomUUID(), LocalDateTime.now().plusMinutes(30));

            ResponseData<String> response = tokenRepository.createToken(tokenData, dslContext);
            if (response.getSuccess()){
                return tokenRepository.findByAccountId(accountId, dslContext);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseData<String> sendResetPasswordEmail(String email) {
        Optional<UsersRecord> user = userRepository.findByEmail(email, dslContext);

        if (user.isPresent()) {
            Result<TokenRecord> response = generateEmailToken(user.get().getId());
            //send email
        }
        return new ResponseData<>(false, "No user found with given email address.");
    }
}
