package com.apuope.apuope_re.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionDataTrueAndFalse {
    public Integer id;
    @JsonProperty("question_number")
    public Integer questionNumber;
    public String question;
    @JsonProperty("option_a")
    public String optionA;
    @JsonProperty("option_b")
    public String optionB;
    @JsonProperty("correct_option")
    public String correctOption;
    public Integer points;

    public QuestionDataTrueAndFalse(Integer id, Integer questionNumber, String question, String optionA, String optionB, String correctOption, Integer points) {
        this.id = id;
        this.questionNumber = questionNumber;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.correctOption = correctOption;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
