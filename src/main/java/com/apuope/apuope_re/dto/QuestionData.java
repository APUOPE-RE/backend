package com.apuope.apuope_re.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionData {
    public Integer id;
    @JsonProperty("question_id")
    public Integer questionId;
    public String question;
    @JsonProperty("option_a")
    public String optionA;
    @JsonProperty("option_b")
    public String optionB;
    @JsonProperty("option_c")
    public String optionC;
    @JsonProperty("correct_option")
    public String correctOption;
    public Integer points;

    public QuestionData() {
    }

    public QuestionData(Integer id, Integer questionId, String question, String optionA, String optionB, String optionC, String correctOption,
            Integer points) {
        this.id = id;
        this.questionId = questionId;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctOption = correctOption;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
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

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
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
