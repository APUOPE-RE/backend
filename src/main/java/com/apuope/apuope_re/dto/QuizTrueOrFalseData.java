package com.apuope.apuope_re.dto;

import java.util.ArrayList;
import java.util.List;

public class QuizTrueOrFalseData {
    public Integer id;
    public Integer accountId;
    public Integer lectureId;
    public Integer maxPoints;
    public List<QuestionDataTrueAndFalse> questionDataTrueAndFalse = new ArrayList<>();

    public QuizTrueOrFalseData(Integer id, Integer accountId, Integer lectureId, Integer maxPoints) {
        this.id = id;
        this.accountId = accountId;
        this.lectureId = lectureId;
        this.maxPoints = maxPoints;
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

    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    public List<QuestionDataTrueAndFalse> getQuestionDataTrueAndFalse() {
        return questionDataTrueAndFalse;
    }

    public void setQuestionDataTrueAndFalse(List<QuestionDataTrueAndFalse> questionDataTrueAndFalse) {
        this.questionDataTrueAndFalse = questionDataTrueAndFalse;
    }
}
