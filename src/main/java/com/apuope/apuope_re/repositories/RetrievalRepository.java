package com.apuope.apuope_re.repositories;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.apuope.apuope_re.jooq.tables.TextbookEmbeddings.TEXTBOOK_EMBEDDINGS;
@Repository
public class RetrievalRepository {

    public RetrievalRepository() {}
    public List<String> getTextChunk(double[] questionEmbedding, List<Integer> chapterIds, DSLContext context) throws SQLException {
        List<String> relevantChunks = new ArrayList<>();

        String embeddingArrayString = "vector '" + Arrays.toString(questionEmbedding) + "'";

        Field<Object> similarityField = DSL.field("embedding <-> " + embeddingArrayString);


        Result<Record1<String>> records = context.select(TEXTBOOK_EMBEDDINGS.CHUNK)
                .from(TEXTBOOK_EMBEDDINGS)
                .where(TEXTBOOK_EMBEDDINGS.CHAPTERID.in(chapterIds))
                .orderBy(similarityField)
                .fetch();

        records.forEach(record -> relevantChunks.add(record.value1()));
        return relevantChunks;
    }

    public List<String> getQuizContext(List<Integer> chapterIds, DSLContext context) throws SQLException {
        List<String> relevantChunks = new ArrayList<>();

        Result<Record1<String>> records = context.select(TEXTBOOK_EMBEDDINGS.CHUNK)
                .from(TEXTBOOK_EMBEDDINGS)
                .where(TEXTBOOK_EMBEDDINGS.CHAPTERID.in(chapterIds))
                .fetch();

        records.forEach(record -> relevantChunks.add(record.value1()));
        return relevantChunks;
    }
}
