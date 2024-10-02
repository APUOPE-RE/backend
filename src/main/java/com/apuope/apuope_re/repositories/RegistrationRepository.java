package com.apuope.apuope_re.repositories;

import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.entities.User;
import com.apuope.apuope_re.jooq.tables.Users;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RegistrationRepository {
    @Autowired
    public RegistrationRepository() {}

    public Optional<User> findByEmail(String email, DSLContext context) {
        return context.select().from(Users.USERS).where(Users.USERS.EMAIL.eq(email)).fetchOptionalInto(User.class);
    }

    public Optional<User> findByUsername(String username, DSLContext context) {
        return context.select().from(Users.USERS).where(Users.USERS.USERNAME.eq(username)).fetchOptionalInto(User.class);
    }

    public ResponseData<String> createUser(RegistrationData registrationData, DSLContext context) {
        try {
            context.insertInto(Users.USERS)
                    .set(Users.USERS.USERNAME, registrationData.getUsername())
                    .set(Users.USERS.EMAIL, registrationData.getEmail())
                    .set(Users.USERS.PASSWORD_HASH, registrationData.getPasswordHash())
                    .execute();

            return new ResponseData<>(true, "User added successfully.");
        } catch (Exception e) {
            return new ResponseData<>(false, "Error when creating user: " + e);
        }
    }
}