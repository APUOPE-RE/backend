package com.apuope.apuope_re.services;

import com.apuope.apuope_re.repositories.RetrievalRepository;
import com.apuope.apuope_re.utils.Constants;
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

    public List<String> findRelevantChunks(double[] questionEmbedding, List<Integer> chapterIds) throws SQLException {
        return retrievalRepository.getTextChunk(questionEmbedding, chapterIds, context);
    }

    public List<String> getQuizContext(Integer lectureId) throws SQLException {
        List<Integer> chapterIds = Constants.LectureChapters.getChaptersByLectureId(lectureId);
        return retrievalRepository.getQuizContext(chapterIds, context);
    }
}