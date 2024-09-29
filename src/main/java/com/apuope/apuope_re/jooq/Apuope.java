/*
 * This file is generated by jOOQ.
 */
package com.apuope.apuope_re.jooq;


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
            Users.USERS
        );
    }
}
