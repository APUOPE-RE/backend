/*
 * This file is generated by jOOQ.
 */
package com.apuope.apuope_re.jooq.tables.records;


import com.apuope.apuope_re.jooq.tables.Token;

import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class TokenRecord extends UpdatableRecordImpl<TokenRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>apuope.token.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>apuope.token.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>apuope.token.account_id</code>.
     */
    public void setAccountId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>apuope.token.account_id</code>.
     */
    public Integer getAccountId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>apuope.token.uuid</code>.
     */
    public void setUuid(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>apuope.token.uuid</code>.
     */
    public UUID getUuid() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>apuope.token.expiration_time</code>.
     */
    public void setExpirationTime(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>apuope.token.expiration_time</code>.
     */
    public LocalDateTime getExpirationTime() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>apuope.token.valid</code>.
     */
    public void setValid(Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>apuope.token.valid</code>.
     */
    public Boolean getValid() {
        return (Boolean) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TokenRecord
     */
    public TokenRecord() {
        super(Token.TOKEN);
    }

    /**
     * Create a detached, initialised TokenRecord
     */
    public TokenRecord(Integer id, Integer accountId, UUID uuid, LocalDateTime expirationTime, Boolean valid) {
        super(Token.TOKEN);

        setId(id);
        setAccountId(accountId);
        setUuid(uuid);
        setExpirationTime(expirationTime);
        setValid(valid);
        resetChangedOnNotNull();
    }
}
