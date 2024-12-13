package com.apuope.apuope_re.dto;

public class QuizSummaryData {
    public Integer quizId;
    public QuizData quizData;
    public QuizResultData quizResultData;

    public QuizSummaryData(){}

    public QuizSummaryData(Integer quizId, QuizData quizData, QuizResultData quizResultData){
        this.quizId = quizId;
        this.quizData = quizData;
        this.quizResultData = quizResultData;
    }

    public Integer getQuizId() { return quizId; }

    public void setQuizId(Integer quizId) { this.quizId = quizId; }

    public QuizData getQuizData() { return quizData; }

    public void setQuizData(QuizData quizData) { this.quizData = quizData; }

    public QuizResultData getQuizResultData() { return quizResultData; }

    public void setQuizResultData(QuizResultData quizResultData) { this.quizResultData = quizResultData; }
}