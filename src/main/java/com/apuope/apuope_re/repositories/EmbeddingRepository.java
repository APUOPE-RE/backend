package com.apuope.apuope_re.repositories;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Array;

import static com.apuope.apuope_re.jooq.tables.TextbookEmbeddings.TEXTBOOK_EMBEDDINGS;

public class EmbeddingRepository {
    @Autowired
    public EmbeddingRepository(){};

    public Result<Record1<String>> findRelevantChuncks(Array embeddingArray, DSLContext context){
        return context.select(TEXTBOOK_EMBEDDINGS.CHUNK)
                .from(TEXTBOOK_EMBEDDINGS)
                .orderBy(TEXTBOOK_EMBEDDINGS.EMBEDDING.sub((Number) embeddingArray).cast(Double.class))
                .limit(5)
                .fetch();
    }
}
