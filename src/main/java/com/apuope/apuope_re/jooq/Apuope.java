/*
 * This file is generated by jOOQ.
 */
package com.apuope.apuope_re.jooq;


import com.apuope.apuope_re.jooq.tables.Conversation;
import com.apuope.apuope_re.jooq.tables.Message;
import com.apuope.apuope_re.jooq.tables.Session;
import com.apuope.apuope_re.jooq.tables.TextbookEmbeddings;
import com.apuope.apuope_re.jooq.tables.Token;
import com.apuope.apuope_re.jooq.tables.Users;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Apuope extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>apuope</code>
     */
    public static final Apuope APUOPE = new Apuope();

    /**
     * The table <code>apuope.conversation</code>.
     */
    public final Conversation CONVERSATION = Conversation.CONVERSATION;

    /**
     * The table <code>apuope.message</code>.
     */
    public final Message MESSAGE = Message.MESSAGE;

    /**
     * The table <code>apuope.session</code>.
     */
    public final Session SESSION = Session.SESSION;

    /**
     * The table <code>apuope.textbook_embeddings</code>.
     */
    public final TextbookEmbeddings TEXTBOOK_EMBEDDINGS = TextbookEmbeddings.TEXTBOOK_EMBEDDINGS;

    /**
     * The table <code>apuope.token</code>.
     */
    public final Token TOKEN = Token.TOKEN;

    /**
     * The table <code>apuope.users</code>.
     */
    public final Users USERS = Users.USERS;

    /**
     * No further instances allowed
     */
    private Apuope() {
        super("apuope", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Conversation.CONVERSATION,
            Message.MESSAGE,
            Session.SESSION,
            TextbookEmbeddings.TEXTBOOK_EMBEDDINGS,
            Token.TOKEN,
            Users.USERS
        );
    }
}
