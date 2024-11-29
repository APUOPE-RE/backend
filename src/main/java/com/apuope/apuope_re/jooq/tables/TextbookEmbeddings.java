/*
 * This file is generated by jOOQ.
 */
package com.apuope.apuope_re.jooq.tables;


import com.apuope.apuope_re.jooq.Apuope;
import com.apuope.apuope_re.jooq.Indexes;
import com.apuope.apuope_re.jooq.Keys;
import com.apuope.apuope_re.jooq.tables.records.TextbookEmbeddingsRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class TextbookEmbeddings extends TableImpl<TextbookEmbeddingsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>apuope.textbook_embeddings</code>
     */
    public static final TextbookEmbeddings TEXTBOOK_EMBEDDINGS = new TextbookEmbeddings();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TextbookEmbeddingsRecord> getRecordType() {
        return TextbookEmbeddingsRecord.class;
    }

    /**
     * The column <code>apuope.textbook_embeddings.id</code>.
     */
    public final TableField<TextbookEmbeddingsRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>apuope.textbook_embeddings.chunk</code>.
     */
    public final TableField<TextbookEmbeddingsRecord, String> CHUNK = createField(DSL.name("chunk"), SQLDataType.CLOB, this, "");

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public final TableField<TextbookEmbeddingsRecord, Object> EMBEDDING = createField(DSL.name("embedding"), DefaultDataType.getDefaultDataType("\"public\".\"vector\""), this, "");

    /**
     * The column <code>apuope.textbook_embeddings.chapterid</code>.
     */
    public final TableField<TextbookEmbeddingsRecord, Integer> CHAPTERID = createField(DSL.name("chapterid"), SQLDataType.INTEGER, this, "");

    private TextbookEmbeddings(Name alias, Table<TextbookEmbeddingsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private TextbookEmbeddings(Name alias, Table<TextbookEmbeddingsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>apuope.textbook_embeddings</code> table reference
     */
    public TextbookEmbeddings(String alias) {
        this(DSL.name(alias), TEXTBOOK_EMBEDDINGS);
    }

    /**
     * Create an aliased <code>apuope.textbook_embeddings</code> table reference
     */
    public TextbookEmbeddings(Name alias) {
        this(alias, TEXTBOOK_EMBEDDINGS);
    }

    /**
     * Create a <code>apuope.textbook_embeddings</code> table reference
     */
    public TextbookEmbeddings() {
        this(DSL.name("textbook_embeddings"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Apuope.APUOPE;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_TEXTBOOK_EMBEDDINGS_CHAPTERID);
    }

    @Override
    public Identity<TextbookEmbeddingsRecord, Integer> getIdentity() {
        return (Identity<TextbookEmbeddingsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<TextbookEmbeddingsRecord> getPrimaryKey() {
        return Keys.TEXTBOOK_EMBEDDINGS_PKEY;
    }

    @Override
    public TextbookEmbeddings as(String alias) {
        return new TextbookEmbeddings(DSL.name(alias), this);
    }

    @Override
    public TextbookEmbeddings as(Name alias) {
        return new TextbookEmbeddings(alias, this);
    }

    @Override
    public TextbookEmbeddings as(Table<?> alias) {
        return new TextbookEmbeddings(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TextbookEmbeddings rename(String name) {
        return new TextbookEmbeddings(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TextbookEmbeddings rename(Name name) {
        return new TextbookEmbeddings(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TextbookEmbeddings rename(Table<?> name) {
        return new TextbookEmbeddings(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextbookEmbeddings where(Condition condition) {
        return new TextbookEmbeddings(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextbookEmbeddings where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextbookEmbeddings where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextbookEmbeddings where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TextbookEmbeddings where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TextbookEmbeddings where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TextbookEmbeddings where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TextbookEmbeddings where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextbookEmbeddings whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextbookEmbeddings whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
