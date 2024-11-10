package com.apuope.apuope_re.services;

import com.apuope.apuope_re.repositories.RetrievalRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RetrievalService {
    private final RetrievalRepository retrievalRepository;
    private final DSLContext context;

    public RetrievalService(RetrievalRepository retrievalRepository, DSLContext context) {
        this.retrievalRepository = retrievalRepository;
        this.context = context;
    }

    public List<String> findRelevantChunks(double[] questionEmbedding) throws SQLException {
        return retrievalRepository.getTextChunk(questionEmbedding, context);
    }
}