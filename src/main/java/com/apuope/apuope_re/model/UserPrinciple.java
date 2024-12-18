package com.apuope.apuope_re.model;

import com.apuope.apuope_re.jooq.tables.records.UsersRecord;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

public class UserPrinciple implements UserDetails {
    private final UsersRecord usersRecord;

    public UserPrinciple(UsersRecord usersRecord) {
        this.usersRecord = usersRecord;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return usersRecord.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return usersRecord.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
