package com.apuope.apuope_re.repositories;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.dto.TokenData;
import com.apuope.apuope_re.jooq.tables.Token;
import com.apuope.apuope_re.jooq.tables.Users;
import com.apuope.apuope_re.jooq.tables.records.TokenRecord;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TokenRepository {
    @Autowired
    public TokenRepository(){};

    public Result<TokenRecord> findByAccountId(Integer accountId, DSLContext context){
        return context.selectFrom(Token.TOKEN)
                .where(Token.TOKEN.ACCOUNT_ID.eq(accountId)).orderBy(Token.TOKEN.ID.desc()).fetch();
    }

    public ResponseData<String> createToken(TokenData tokenData, DSLContext context) {
        try {
            context.insertInto(Token.TOKEN)
                    .set(Token.TOKEN.ACCOUNT_ID, tokenData.getAccountId())
                    .set(Token.TOKEN.UUID, tokenData.getUuid())
                    .set(Token.TOKEN.EXPIRATION_TIME, tokenData.getExpirationTime())
                    .execute();

            return new ResponseData<>(true, "Token created successfully.");
        } catch (Exception e) {
            return new ResponseData<>(false, "Error when creating token: " + e);
        }
    }
}
