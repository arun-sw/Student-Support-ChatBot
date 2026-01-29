package com.example.studentsupportchartbot;

public class ChatResponse {
    private String message;
    private boolean isUser;

    public ChatResponse(String message, boolean isUser) {
        this.message = message;
        this.isUser = isUser;
    }

    public String getMessage() {
        return message;
    }

    public boolean isUser() {
        return isUser;
    }
}
