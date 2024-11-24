package com.apuope.apuope_re.dto;

import java.util.List;

public class QuizSubmitData {
    public Integer quizId;
    public List<QuizSubmitAnswerData> quizSubmitAnswerDataList;

    public QuizSubmitData(Integer quizId, List<QuizSubmitAnswerData> quizSubmitAnswerDataList) {
        this.quizId = quizId;
        this.quizSubmitAnswerDataList = quizSubmitAnswerDataList;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public List<QuizSubmitAnswerData> getQuestionAnswerDataList() {
        return quizSubmitAnswerDataList;
    }

    public void setAnswer(List<QuizSubmitAnswerData> quizSubmitAnswerDataList) {
        this.quizSubmitAnswerDataList = quizSubmitAnswerDataList;
    }
}
