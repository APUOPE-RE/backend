package com.apuope.apuope_re.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuizResultData {
    public Integer id;
    public Integer accountId;
    public Integer quizId;
    public Integer score;
    public Integer maxScore;
    public LocalDateTime dateTime;
    public List<QuizAnswerData> quizAnswerDataList = new ArrayList<>();

    public QuizResultData() {}

    public QuizResultData(Integer id, Integer accountId, Integer quizId, Integer score, Integer maxScore, LocalDateTime dateTime) {
        this.id = id;
        this.accountId = accountId;
        this.quizId = quizId;
        this.score = score;
        this.maxScore = maxScore;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<QuizAnswerData> getQuizAnswerDataList() {
        return quizAnswerDataList;
    }

    public void setQuizAnswerDataList(List<QuizAnswerData> quizAnswerDataList) {
        this.quizAnswerDataList = quizAnswerDataList;
    }
}
