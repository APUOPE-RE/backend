package com.apuope.apuope_re.dto;

public class ChatRequestData {
    public Integer userId;
    public String data;

    public ChatRequestData(Integer userId, String data) {
        this.userId = userId;
        this.data = data;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
