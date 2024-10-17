package com.apuope.apuope_re.services;

import com.apuope.apuope_re.jooq.tables.Session;
import com.apuope.apuope_re.jooq.tables.Token;
import com.apuope.apuope_re.jooq.tables.Users;
import com.apuope.apuope_re.jooq.tables.records.TokenRecord;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import org.jooq.DSLContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

public class TestDataGenerator {
    public static void insertTestUser(DSLContext dslContext, String email,
            String username, String passwordHash,
            boolean verified) {
        Optional<UsersRecord> user =
                dslContext.selectFrom(Users.USERS).where(Users.USERS.EMAIL.eq(email)).fetchOptional();

        if (user.isEmpty()) {
            dslContext.insertInto(Users.USERS).set(Users.USERS.USERNAME, username).set(Users.USERS.EMAIL, email).set(Users.USERS.PASSWORD_HASH, passwordHash).set(Users.USERS.VERIFIED, verified).set(Users.USERS.VERIFIED, true)
                .execute();
        }
    }

    public static UsersRecord findByEmail(DSLContext dslContext, String email){
        return dslContext.selectFrom(Users.USERS)
                .where(Users.USERS.EMAIL.eq(email))
                .fetch().getFirst();
    }

    public static void insertTestToken(DSLContext dslContext, Integer accountId, UUID uuid, LocalDateTime expirationTime, boolean valid) {
            dslContext.insertInto(Token.TOKEN).set(Token.TOKEN.ACCOUNT_ID, accountId).set(Token.TOKEN.UUID, uuid).set(Token.TOKEN.EXPIRATION_TIME,
                    expirationTime).set(Token.TOKEN.VALID, valid).execute();
    }

    public static TokenRecord fetchUnexpiredAndValidToken(DSLContext dslContext) {
        return dslContext.selectFrom(Token.TOKEN)
                .where(Token.TOKEN.VALID.eq(true))
                .and(Token.TOKEN.EXPIRATION_TIME.gt(LocalDateTime.now(ZoneId.of("Europe/Helsinki"))))
                .fetch().getFirst();
    }

    public static TokenRecord fetchExpiredToken(DSLContext dslContext) {
        return dslContext.selectFrom(Token.TOKEN)
                .where(Token.TOKEN.EXPIRATION_TIME.lt(LocalDateTime.now(ZoneId.of("Europe/Helsinki"))))
                .fetch().getFirst();
    }

    public static TokenRecord fetchInvalidToken(DSLContext dslContext) {
        return dslContext.selectFrom(Token.TOKEN)
                .where(Token.TOKEN.VALID.eq(false))
                .fetch().getFirst();
    }

    public static void deleteTestUsers(DSLContext dslContext, String email) {
        Optional<UsersRecord> user =
                dslContext.selectFrom(Users.USERS).where(Users.USERS.EMAIL.eq(email)).fetchOptional();

        if (user.isPresent()){
            dslContext.delete(Session.SESSION).where(Session.SESSION.ACCOUNT_ID.eq(user.get().getId())).execute();
            dslContext.delete(Session.SESSION).execute();
            dslContext.delete(Token.TOKEN).where(Token.TOKEN.ACCOUNT_ID.eq(user.get().getId())).execute();
            dslContext.delete(Users.USERS).where(Users.USERS.ID.eq(user.get().getId())).execute();
        }
    }
}
