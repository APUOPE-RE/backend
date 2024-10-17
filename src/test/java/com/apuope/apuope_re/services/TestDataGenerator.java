package com.apuope.apuope_re.services;

import com.apuope.apuope_re.jooq.tables.Session;
import com.apuope.apuope_re.jooq.tables.Users;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.util.Optional;

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

    public static void deleteTestUsers(DSLContext dslContext, String email) {
        Optional<UsersRecord> user =
                dslContext.selectFrom(Users.USERS).where(Users.USERS.EMAIL.eq(email)).fetchOptional();

        if (user.isPresent()){
            dslContext.delete(Session.SESSION).where(Session.SESSION.ACCOUNT_ID.eq(user.get().getId())).execute();
            dslContext.delete(Session.SESSION).execute();
        dslContext.delete(Users.USERS).where(Users.USERS.ID.eq(user.get().getId())).execute();
        }
    }
}
