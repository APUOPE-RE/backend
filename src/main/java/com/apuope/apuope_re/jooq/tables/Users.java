/*
 * This file is generated by jOOQ.
 */
package com.apuope.apuope_re.jooq.tables;


import com.apuope.apuope_re.jooq.Apuope;
import com.apuope.apuope_re.jooq.Keys;
import com.apuope.apuope_re.jooq.tables.Conversation.ConversationPath;
import com.apuope.apuope_re.jooq.tables.Session.SessionPath;
import com.apuope.apuope_re.jooq.tables.Token.TokenPath;
import com.apuope.apuope_re.jooq.tables.records.UsersRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Users extends TableImpl<UsersRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>apuope.users</code>
     */
    public static final Users USERS = new Users();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UsersRecord> getRecordType() {
        return UsersRecord.class;
    }

    /**
     * The column <code>apuope.users.id</code>.
     */
    public final TableField<UsersRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>apuope.users.uuid</code>.
     */
    public final TableField<UsersRecord, java.util.UUID> UUID = createField(DSL.name("uuid"), SQLDataType.UUID.defaultValue(DSL.field(DSL.raw("gen_random_uuid()"), SQLDataType.UUID)), this, "");

    /**
     * The column <code>apuope.users.username</code>.
     */
    public final TableField<UsersRecord, String> USERNAME = createField(DSL.name("username"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>apuope.users.email</code>.
     */
    public final TableField<UsersRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>apuope.users.password_hash</code>.
     */
    public final TableField<UsersRecord, String> PASSWORD_HASH = createField(DSL.name("password_hash"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>apuope.users.created</code>.
     */
    public final TableField<UsersRecord, LocalDateTime> CREATED = createField(DSL.name("created"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>apuope.users.updated</code>.
     */
    public final TableField<UsersRecord, LocalDateTime> UPDATED = createField(DSL.name("updated"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>apuope.users.verified</code>.
     */
    public final TableField<UsersRecord, Boolean> VERIFIED = createField(DSL.name("verified"), SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    private Users(Name alias, Table<UsersRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Users(Name alias, Table<UsersRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>apuope.users</code> table reference
     */
    public Users(String alias) {
        this(DSL.name(alias), USERS);
    }

    /**
     * Create an aliased <code>apuope.users</code> table reference
     */
    public Users(Name alias) {
        this(alias, USERS);
    }

    /**
     * Create a <code>apuope.users</code> table reference
     */
    public Users() {
        this(DSL.name("users"), null);
    }

    public <O extends Record> Users(Table<O> path, ForeignKey<O, UsersRecord> childPath, InverseForeignKey<O, UsersRecord> parentPath) {
        super(path, childPath, parentPath, USERS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class UsersPath extends Users implements Path<UsersRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> UsersPath(Table<O> path, ForeignKey<O, UsersRecord> childPath, InverseForeignKey<O, UsersRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private UsersPath(Name alias, Table<UsersRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public UsersPath as(String alias) {
            return new UsersPath(DSL.name(alias), this);
        }

        @Override
        public UsersPath as(Name alias) {
            return new UsersPath(alias, this);
        }

        @Override
        public UsersPath as(Table<?> alias) {
            return new UsersPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Apuope.APUOPE;
    }

    @Override
    public Identity<UsersRecord, Integer> getIdentity() {
        return (Identity<UsersRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<UsersRecord> getPrimaryKey() {
        return Keys.USERS_PKEY;
    }

    @Override
    public List<UniqueKey<UsersRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.USERS_EMAIL_KEY, Keys.USERS_USERNAME_KEY, Keys.USERS_UUID_KEY);
    }

    private transient ConversationPath _conversation;

    /**
     * Get the implicit to-many join path to the
     * <code>apuope.conversation</code> table
     */
    public ConversationPath conversation() {
        if (_conversation == null)
            _conversation = new ConversationPath(this, null, Keys.CONVERSATION__FK_ACCOUNT.getInverseKey());

        return _conversation;
    }

    private transient SessionPath _session;

    /**
     * Get the implicit to-many join path to the <code>apuope.session</code>
     * table
     */
    public SessionPath session() {
        if (_session == null)
            _session = new SessionPath(this, null, Keys.SESSION__FK_ACCOUNT.getInverseKey());

        return _session;
    }

    private transient TokenPath _token;

    /**
     * Get the implicit to-many join path to the <code>apuope.token</code> table
     */
    public TokenPath token() {
        if (_token == null)
            _token = new TokenPath(this, null, Keys.TOKEN__FK_ACCOUNT.getInverseKey());

        return _token;
    }

    @Override
    public Users as(String alias) {
        return new Users(DSL.name(alias), this);
    }

    @Override
    public Users as(Name alias) {
        return new Users(alias, this);
    }

    @Override
    public Users as(Table<?> alias) {
        return new Users(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Users rename(String name) {
        return new Users(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Users rename(Name name) {
        return new Users(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Users rename(Table<?> name) {
        return new Users(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Users where(Condition condition) {
        return new Users(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Users where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Users where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Users where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Users where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Users where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Users where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Users where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Users whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Users whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
