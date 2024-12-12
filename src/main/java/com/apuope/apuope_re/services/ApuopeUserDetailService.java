package com.apuope.apuope_re.services;

import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import com.apuope.apuope_re.model.UserPrinciple;
import com.apuope.apuope_re.repositories.UserRepository;
import org.jooq.DSLContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApuopeUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final DSLContext dslContext;

    public ApuopeUserDetailService(UserRepository userRepository, DSLContext dslContext) {
        this.userRepository = userRepository;
        this.dslContext = dslContext;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersRecord usersRecord = userRepository.findByEmail(email, dslContext);
        return new UserPrinciple(usersRecord);
    }
}
