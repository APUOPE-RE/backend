/*
 * This file is generated by jOOQ.
 */
package com.apuope.apuope_re.jooq;


import com.apuope.apuope_re.jooq.tables.TextbookEmbeddings;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in apuope.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index IDX_TEXTBOOK_EMBEDDINGS_CHAPTERID = Internal.createIndex(DSL.name("idx_textbook_embeddings_chapterid"), TextbookEmbeddings.TEXTBOOK_EMBEDDINGS, new OrderField[] { TextbookEmbeddings.TEXTBOOK_EMBEDDINGS.CHAPTERID }, false);
}