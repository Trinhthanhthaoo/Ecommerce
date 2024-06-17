package com.vku.Model;

import java.sql.Timestamp;

public class Chat {
    private int chatId;
    private int userId;
    private String message;
    private Timestamp timestamp;

    public Chat() {}

    public Chat(int chatId, int userId, String message, Timestamp timestamp) {
        this.chatId = chatId;
        this.userId = userId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
