package com.apuope.apuope_re.repositories;

import com.apuope.apuope_re.dto.QuizData;
import com.apuope.apuope_re.dto.RegistrationData;
import com.apuope.apuope_re.dto.ResponseData;
import com.apuope.apuope_re.jooq.tables.Session;
import com.apuope.apuope_re.jooq.tables.Users;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {
    private final ZoneId TIMEZONE = ZoneId.of("Europe/Helsinki");
    @Autowired
    public UserRepository(){};

    public UsersRecord findVerifiedUserByEmail(String email, DSLContext context){
        return context.selectFrom(Users.USERS)
                .where(Users.USERS.EMAIL.eq(email).and(Users.USERS.VERIFIED.eq(true)))
                .limit(1)
                .fetchOneInto(UsersRecord.class);
    }

    public UsersRecord findByEmail(String email, DSLContext context) {
        return context.selectFrom(Users.USERS)
                .where(Users.USERS.EMAIL.eq(email))
                .limit(1)
                .fetchOneInto(UsersRecord.class);
    }

    public UsersRecord findByUsername(String username, DSLContext context) {
        return context.selectFrom(Users.USERS)
                .where(Users.USERS.USERNAME.eq(username))
                .limit(1)
                .fetchOneInto(UsersRecord.class);
    }

    public ResponseData<Object> createUser(RegistrationData registrationData, DSLContext context) {
        try {
            context.insertInto(Users.USERS)
                    .set(Users.USERS.USERNAME, registrationData.getUsername())
                    .set(Users.USERS.EMAIL, registrationData.getEmail())
                    .set(Users.USERS.PASSWORD_HASH, registrationData.getPasswordHash())
                    .set(Users.USERS.CREATED, LocalDateTime.now(TIMEZONE))
                    .set(Users.USERS.UPDATED, LocalDateTime.now(TIMEZONE))
                    .execute();

            return new ResponseData<>(true, "User added successfully.");
        } catch (Exception e) {
            return new ResponseData<>(false, "Error when creating user: " + e);
        }
    }

    public Boolean alterUserVerify(UUID uuid, DSLContext context) {
        int affectedRows = context.update(Users.USERS)
                .set(Users.USERS.VERIFIED, true)
                .set(Users.USERS.UPDATED, LocalDateTime.now(TIMEZONE))
                .where(Users.USERS.UUID.eq(uuid)).and(Users.USERS.VERIFIED.eq(false))
                .execute();

        return affectedRows > 0;
    }

    public Boolean alterUserResetPassword(Integer id, String passwordHash, DSLContext context) {
        int affectedRows = context.update(Users.USERS)
                .set(Users.USERS.PASSWORD_HASH, passwordHash)
                .set(Users.USERS.UPDATED, LocalDateTime.now(TIMEZONE))
                .where(Users.USERS.ID.eq(id))
                .execute();

        return affectedRows > 0;
    }

    public void addSession(int UserId, DSLContext context){
        context.insertInto(Session.SESSION)
                .set(Session.SESSION.ACCOUNT_ID, UserId)
                .set(Session.SESSION.LOG_IN, java.time.LocalDateTime.now())
                .execute();
    }

    public void logOutSession(int userId, DSLContext context) {
        context.update(Session.SESSION)
                .set(Session.SESSION.LOG_OUT, java.time.LocalDateTime.now())
                .where(Session.SESSION.ACCOUNT_ID.eq(userId).and(Session.SESSION.LOG_OUT.isNull()))
                .execute();
    }
}
