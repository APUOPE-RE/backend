/*
 * This file is generated by jOOQ.
 */
package com.apuope.apuope_re.jooq.tables;


import com.apuope.apuope_re.jooq.Apuope;
import com.apuope.apuope_re.jooq.Keys;
import com.apuope.apuope_re.jooq.tables.Message.MessagePath;
import com.apuope.apuope_re.jooq.tables.Users.UsersPath;
import com.apuope.apuope_re.jooq.tables.records.ConversationRecord;

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
public class Conversation extends TableImpl<ConversationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>apuope.conversation</code>
     */
    public static final Conversation CONVERSATION = new Conversation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ConversationRecord> getRecordType() {
        return ConversationRecord.class;
    }

    /**
     * The column <code>apuope.conversation.id</code>.
     */
    public final TableField<ConversationRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>apuope.conversation.account_id</code>.
     */
    public final TableField<ConversationRecord, Integer> ACCOUNT_ID = createField(DSL.name("account_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>apuope.conversation.chapter_id</code>.
     */
    public final TableField<ConversationRecord, Integer> CHAPTER_ID = createField(DSL.name("chapter_id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>apuope.conversation.datetime</code>.
     */
    public final TableField<ConversationRecord, LocalDateTime> DATETIME = createField(DSL.name("datetime"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>apuope.conversation.subject</code>.
     */
    public final TableField<ConversationRecord, String> SUBJECT = createField(DSL.name("subject"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    private Conversation(Name alias, Table<ConversationRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Conversation(Name alias, Table<ConversationRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>apuope.conversation</code> table reference
     */
    public Conversation(String alias) {
        this(DSL.name(alias), CONVERSATION);
    }

    /**
     * Create an aliased <code>apuope.conversation</code> table reference
     */
    public Conversation(Name alias) {
        this(alias, CONVERSATION);
    }

    /**
     * Create a <code>apuope.conversation</code> table reference
     */
    public Conversation() {
        this(DSL.name("conversation"), null);
    }

    public <O extends Record> Conversation(Table<O> path, ForeignKey<O, ConversationRecord> childPath, InverseForeignKey<O, ConversationRecord> parentPath) {
        super(path, childPath, parentPath, CONVERSATION);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class ConversationPath extends Conversation implements Path<ConversationRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> ConversationPath(Table<O> path, ForeignKey<O, ConversationRecord> childPath, InverseForeignKey<O, ConversationRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private ConversationPath(Name alias, Table<ConversationRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public ConversationPath as(String alias) {
            return new ConversationPath(DSL.name(alias), this);
        }

        @Override
        public ConversationPath as(Name alias) {
            return new ConversationPath(alias, this);
        }

        @Override
        public ConversationPath as(Table<?> alias) {
            return new ConversationPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Apuope.APUOPE;
    }

    @Override
    public Identity<ConversationRecord, Integer> getIdentity() {
        return (Identity<ConversationRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ConversationRecord> getPrimaryKey() {
        return Keys.CONVERSATION_PKEY;
    }

    @Override
    public List<ForeignKey<ConversationRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONVERSATION__FK_ACCOUNT);
    }

    private transient UsersPath _users;

    /**
     * Get the implicit join path to the <code>apuope.users</code> table.
     */
    public UsersPath users() {
        if (_users == null)
            _users = new UsersPath(this, Keys.CONVERSATION__FK_ACCOUNT, null);

        return _users;
    }

    private transient MessagePath _message;

    /**
     * Get the implicit to-many join path to the <code>apuope.message</code>
     * table
     */
    public MessagePath message() {
        if (_message == null)
            _message = new MessagePath(this, null, Keys.MESSAGE__FK_ACCOUNT.getInverseKey());

        return _message;
    }

    @Override
    public Conversation as(String alias) {
        return new Conversation(DSL.name(alias), this);
    }

    @Override
    public Conversation as(Name alias) {
        return new Conversation(alias, this);
    }

    @Override
    public Conversation as(Table<?> alias) {
        return new Conversation(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Conversation rename(String name) {
        return new Conversation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Conversation rename(Name name) {
        return new Conversation(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Conversation rename(Table<?> name) {
        return new Conversation(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Conversation where(Condition condition) {
        return new Conversation(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Conversation where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Conversation where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Conversation where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Conversation where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Conversation where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Conversation where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Conversation where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Conversation whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Conversation whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
