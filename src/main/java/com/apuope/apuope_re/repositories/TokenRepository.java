package com.apuope.apuope_re.repositories;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.dto.TokenData;
import com.apuope.apuope_re.jooq.tables.Token;
import com.apuope.apuope_re.jooq.tables.records.TokenRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Repository
public class TokenRepository {
    private final ZoneId TIMEZONE = ZoneId.of("Europe/Helsinki");
    @Autowired
    public TokenRepository(){};

    public TokenRecord findByAccountId(Integer accountId, DSLContext context){
        return context.selectFrom(Token.TOKEN)
                .where(Token.TOKEN.ACCOUNT_ID.eq(accountId)).orderBy(Token.TOKEN.ID.desc()).fetch().getFirst();
    }

    public TokenRecord findByUuid(UUID uuid, DSLContext context){
        return context.selectFrom(Token.TOKEN)
                .where(Token.TOKEN.UUID.eq(uuid)).fetch().getFirst();
    }

    public ResponseData<String> createToken(TokenData tokenData, DSLContext context) {
        try {
            context.insertInto(Token.TOKEN)
                    .set(Token.TOKEN.ACCOUNT_ID, tokenData.getAccountId())
                    .set(Token.TOKEN.UUID, tokenData.getUuid())
                    .set(Token.TOKEN.EXPIRATION_TIME, LocalDateTime.now(TIMEZONE).plusMinutes(30))
                    .set(Token.TOKEN.VALID, true)
                    .execute();

            return new ResponseData<>(true, "Token created successfully.");
        } catch (Exception e) {
            return new ResponseData<>(false, "Error when creating token: " + e);
        }
    }

    public void invalidateToken(Integer id, DSLContext context) {
        try {
            context.update(Token.TOKEN)
                    .set(Token.TOKEN.VALID, false)
                    .where(Token.TOKEN.ID.eq(id))
                    .execute();

        } catch (Exception e) {
            System.out.println("Error when invalidating token: " + e);
        }
    }
}
