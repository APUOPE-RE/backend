package com.apuope.apuope_re.dto;

import java.util.UUID;

public class ResetPasswordData {
    public UUID uuid;
    public String password;

    public ResetPasswordData(UUID uuid, String passwordHash) {
        this.uuid = uuid;
        this.password = passwordHash;
    }

    public UUID getUuid() { return uuid; }

    public void setUuid(UUID uuid) { this.uuid = uuid; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
