package com.apuope.apuope_re.dto;

public class QuizAnswerData {
    public Integer id;
    public Integer quizResultId;
    public Integer questionId;
    public Integer questionNumber;
    public String answer;
    public boolean correct;
    public Integer points;

    public QuizAnswerData(Integer id, Integer quizResultId, Integer questionId, Integer questionNumber, String answer, boolean correct,
            Integer points) {
        this.id = id;
        this.quizResultId = quizResultId;
        this.questionId = questionId;
        this.questionNumber = questionNumber;
        this.answer = answer;
        this.correct = correct;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuizResultId() {
        return quizResultId;
    }

    public void setQuizResultId(Integer quizResultId) {
        this.quizResultId = quizResultId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
