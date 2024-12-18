/*
 * This file is generated by jOOQ.
 */
package com.apuope.apuope_re.jooq.tables.records;


import com.apuope.apuope_re.jooq.tables.Session;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class SessionRecord extends UpdatableRecordImpl<SessionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>apuope.session.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>apuope.session.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>apuope.session.account_id</code>.
     */
    public void setAccountId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>apuope.session.account_id</code>.
     */
    public Integer getAccountId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>apuope.session.log_in</code>.
     */
    public void setLogIn(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>apuope.session.log_in</code>.
     */
    public LocalDateTime getLogIn() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>apuope.session.log_out</code>.
     */
    public void setLogOut(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>apuope.session.log_out</code>.
     */
    public LocalDateTime getLogOut() {
        return (LocalDateTime) get(3);
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
     * Create a detached SessionRecord
     */
    public SessionRecord() {
        super(Session.SESSION);
    }

    /**
     * Create a detached, initialised SessionRecord
     */
    public SessionRecord(Integer id, Integer accountId, LocalDateTime logIn, LocalDateTime logOut) {
        super(Session.SESSION);

        setId(id);
        setAccountId(accountId);
        setLogIn(logIn);
        setLogOut(logOut);
        resetChangedOnNotNull();
    }
}
