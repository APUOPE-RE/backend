package com.apuope.apuope_re.services;

import com.apuope.apuope_re.jooq.tables.Users;
import org.jooq.DSLContext;

public class TestDataGenerator {
    public static void insertTestUser(DSLContext dslContext, String email, String passwordHash) {
        dslContext.insertInto(Users.USERS)
                .set(Users.USERS.USERNAME, "testuser")
                .set(Users.USERS.EMAIL, email)
                .set(Users.USERS.PASSWORD_HASH, passwordHash)
                .execute();
    }

    public static void deleteTestUsers(DSLContext dslContext) {
        dslContext.delete(Users.USERS).execute();
    }
}
