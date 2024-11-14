package com.apuope.apuope_re.dto;

public class ChatRequestData {
    public Integer conversationId;
    public Integer chapterId;
    public String data;

    public ChatRequestData(Integer conversationId, Integer chapterId, String data) {
        this.conversationId = conversationId;
        this.chapterId = chapterId;
        this.data = data;
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
