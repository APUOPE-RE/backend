package com.apuope.apuope_re.services;

import com.apuope.apuope_re.dto.TextChunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RetrievalService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
/*
    public List<String> findRelevantChunks(double[] questionEmbedding) {
        List<String> relevantChunks = new ArrayList<>();

        jdbcTemplate.execute((Connection connection) -> {
            // Prepare the SQL query with pgvector for similarity search
            String query = "SELECT chunk, embedding FROM textbook_embeddings ORDER BY embedding <-> ? LIMIT 5";
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                // Convert questionEmbedding to SQL array format for the query
                Array embeddingArray = connection.createArrayOf("float8", questionEmbedding);
                statement.setArray(1, embeddingArray);

                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    String chunkText = rs.getString("chunk");
                    relevantChunks.add(new TextChunk(chunkText));
                }
            }
            return null;
        });

        return relevantChunks;
    }

 */
}