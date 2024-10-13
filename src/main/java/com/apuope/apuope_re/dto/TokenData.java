package com.apuope.apuope_re.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

public class TokenData {
    public Integer account_id;
    public UUID uuid;
    public LocalDateTime expiration_time;

    public TokenData(Integer account_id, UUID uuid, LocalDateTime expiration_time) {
        this.account_id = account_id;
        this.uuid = uuid;
        this.expiration_time = expiration_time;
    }

    public Integer getAccountId() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getExpirationTime() {
        return expiration_time;
    }

    public void setExpiration_time(LocalDateTime expiration_time) {
        this.expiration_time = expiration_time;
    }
}
