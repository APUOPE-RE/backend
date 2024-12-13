package com.apuope.apuope_re.dto;

import java.util.ArrayList;
import java.util.List;

public class QuizData {
    public Integer id;
    public Integer accountId;
    public Integer lectureId;
    public Integer maxPoints;
    public List<QuestionData> questionDataList = new ArrayList<>();

    public QuizData(Integer id, Integer accountId, Integer lectureId, Integer maxPoints) {
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

    public List<QuestionData> getQuestionDataList() {
        return questionDataList;
    }

    public void setQuestionDataList(List<QuestionData> questionDataList) {
        this.questionDataList = questionDataList;
    }
}
