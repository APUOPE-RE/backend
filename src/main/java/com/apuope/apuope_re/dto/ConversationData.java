package com.apuope.apuope_re.dto;

import java.time.LocalDateTime;

public class ConversationData {
    public Integer id;
    public Integer accountId;
    public Integer chapterId;
    public LocalDateTime dateTime;
    public String subject;

    public ConversationData (Integer id, Integer accountId, Integer chapterId, LocalDateTime dateTime, String subject){
        this.id = id;
        this.accountId = accountId;
        this.chapterId = chapterId;
        this.dateTime = dateTime;
        this.subject = subject;
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

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
