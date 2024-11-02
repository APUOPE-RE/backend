package com.apuope.apuope_re.dto;

import java.time.LocalDateTime;

public class MessageData {
    public Integer conversationId;
    public Integer messageId;
    public String content;
    public Integer source;
    public LocalDateTime timeStamp;

    public MessageData(Integer conversationId, Integer messageId, String content, Integer source, LocalDateTime timeStamp) {
        this.conversationId = conversationId;
        this.messageId = messageId;
        this.content = content;
        this.source = source;
        this.timeStamp = timeStamp;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public LocalDateTime getData() {
        return timeStamp;
    }

    public void setData(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
