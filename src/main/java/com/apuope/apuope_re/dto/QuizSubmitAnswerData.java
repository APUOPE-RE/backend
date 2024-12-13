package com.apuope.apuope_re.dto;

public class QuizSubmitAnswerData {
    public Integer questionNumber;
    public String answer;

    public QuizSubmitAnswerData(Integer questionNumber, String answer) {
        this.questionNumber = questionNumber;
        this.answer = answer;
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
}
