package com.example.websocket_poc.model;

public class ChatMessage {
    private String senderId;
    private String text;

    public ChatMessage() {} // construtor vazio obrigatório

    public ChatMessage(String senderId, String text) {
        this.senderId = senderId;
        this.text = text;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
