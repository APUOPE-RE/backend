package com.apuope.apuope_re.dto;

import java.util.UUID;

public class TokenData {
    public Integer account_id;
    public UUID uuid;

    public TokenData(Integer account_id, UUID uuid) {
        this.account_id = account_id;
        this.uuid = uuid;
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
}
