/*
 * This file is generated by jOOQ.
 */
package com.apuope.apuope_re.jooq.tables;


import com.apuope.apuope_re.jooq.Apuope;
import com.apuope.apuope_re.jooq.Keys;
import com.apuope.apuope_re.jooq.tables.Conversation.ConversationPath;
import com.apuope.apuope_re.jooq.tables.records.MessageRecord;

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
public class Message extends TableImpl<MessageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>apuope.message</code>
     */
    public static final Message MESSAGE = new Message();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MessageRecord> getRecordType() {
        return MessageRecord.class;
    }

    /**
     * The column <code>apuope.message.id</code>.
     */
    public final TableField<MessageRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>apuope.message.conversation_id</code>.
     */
    public final TableField<MessageRecord, Integer> CONVERSATION_ID = createField(DSL.name("conversation_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>apuope.message.content</code>.
     */
    public final TableField<MessageRecord, String> CONTENT = createField(DSL.name("content"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>apuope.message.source</code>.
     */
    public final TableField<MessageRecord, Integer> SOURCE = createField(DSL.name("source"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>apuope.message.datetime</code>.
     */
    public final TableField<MessageRecord, LocalDateTime> DATETIME = createField(DSL.name("datetime"), SQLDataType.LOCALDATETIME(6), this, "");

    private Message(Name alias, Table<MessageRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Message(Name alias, Table<MessageRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>apuope.message</code> table reference
     */
    public Message(String alias) {
        this(DSL.name(alias), MESSAGE);
    }

    /**
     * Create an aliased <code>apuope.message</code> table reference
     */
    public Message(Name alias) {
        this(alias, MESSAGE);
    }

    /**
     * Create a <code>apuope.message</code> table reference
     */
    public Message() {
        this(DSL.name("message"), null);
    }

    public <O extends Record> Message(Table<O> path, ForeignKey<O, MessageRecord> childPath, InverseForeignKey<O, MessageRecord> parentPath) {
        super(path, childPath, parentPath, MESSAGE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class MessagePath extends Message implements Path<MessageRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> MessagePath(Table<O> path, ForeignKey<O, MessageRecord> childPath, InverseForeignKey<O, MessageRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private MessagePath(Name alias, Table<MessageRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public MessagePath as(String alias) {
            return new MessagePath(DSL.name(alias), this);
        }

        @Override
        public MessagePath as(Name alias) {
            return new MessagePath(alias, this);
        }

        @Override
        public MessagePath as(Table<?> alias) {
            return new MessagePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Apuope.APUOPE;
    }

    @Override
    public Identity<MessageRecord, Integer> getIdentity() {
        return (Identity<MessageRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<MessageRecord> getPrimaryKey() {
        return Keys.MESSAGE_PKEY;
    }

    @Override
    public List<ForeignKey<MessageRecord, ?>> getReferences() {
        return Arrays.asList(Keys.MESSAGE__FK_ACCOUNT);
    }

    private transient ConversationPath _conversation;

    /**
     * Get the implicit join path to the <code>apuope.conversation</code> table.
     */
    public ConversationPath conversation() {
        if (_conversation == null)
            _conversation = new ConversationPath(this, Keys.MESSAGE__FK_ACCOUNT, null);

        return _conversation;
    }

    @Override
    public Message as(String alias) {
        return new Message(DSL.name(alias), this);
    }

    @Override
    public Message as(Name alias) {
        return new Message(alias, this);
    }

    @Override
    public Message as(Table<?> alias) {
        return new Message(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Message rename(String name) {
        return new Message(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Message rename(Name name) {
        return new Message(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Message rename(Table<?> name) {
        return new Message(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Message where(Condition condition) {
        return new Message(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Message where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Message where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Message where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Message where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Message where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Message where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Message where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Message whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Message whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
