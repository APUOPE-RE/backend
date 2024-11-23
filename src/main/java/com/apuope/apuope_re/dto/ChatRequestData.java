package com.apuope.apuope_re.dto;

public class ChatRequestData {
    public Integer conversationId;
    public Integer lectureId;
    public String data;

    public ChatRequestData(Integer conversationId, Integer lectureId, String data) {
        this.conversationId = conversationId;
        this.lectureId = lectureId;
        this.data = data;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }


    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
