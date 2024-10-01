package com.apuope.apuope_re.repositories;

import com.apuope.apuope_re.entities.User;
import com.apuope.apuope_re.jooq.tables.Users;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserRepository {
    @Autowired
    public UserRepository(){};

    public Optional<User> findByEmailAndPasswordHash(String email, String passwordHash, DSLContext context){
        return context.select().from(Users.USERS)
                .where(Users.USERS.EMAIL.eq(email).and(Users.USERS.PASSWORD_HASH.eq(passwordHash)))
                .fetchOptionalInto(User.class);
    }
}