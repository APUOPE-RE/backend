package com.apuope.apuope_re.dto;

public class ChatRequestData {
    public Integer userId;
    public Integer conversationId;
    public Integer chapterId;
    public String data;

    public ChatRequestData(Integer userId, Integer conversationId, Integer chapterId, String data) {
        this.userId = userId;
        this.conversationId = conversationId;
        this.chapterId = chapterId;
        this.data = data;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }


    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
