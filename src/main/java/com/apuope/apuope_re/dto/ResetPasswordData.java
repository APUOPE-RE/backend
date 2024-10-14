package com.apuope.apuope_re.dto;

import java.util.UUID;

public class ResetPasswordData {
    public UUID uuid;
    public String passwordHash;

    public ResetPasswordData(UUID uuid, String passwordHash) {
        this.uuid = uuid;
        this.passwordHash = passwordHash;
    }

    public UUID getUuid() { return uuid; }

    public void setUuid(UUID uuid) { this.uuid = uuid; }

    public String getPasswordHash() { return passwordHash; }

    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
