package com.apuope.apuope_re.services;

import com.apuope.apuope_re.jooq.tables.Session;
import com.apuope.apuope_re.jooq.tables.Users;
import org.jooq.DSLContext;

public class TestDataGenerator {
    public static void insertTestUser(DSLContext dslContext, String email, String passwordHash) {
        dslContext.insertInto(Users.USERS)
                .set(Users.USERS.USERNAME, "testuser")
                .set(Users.USERS.EMAIL, email)
                .set(Users.USERS.PASSWORD_HASH, passwordHash)
                .set(Users.USERS.VERIFIED, true)
                .execute();
    }

    public static void deleteTestUsers(DSLContext dslContext) {
        dslContext.delete(Session.SESSION).execute();
        dslContext.delete(Users.USERS).execute();
    }
}
