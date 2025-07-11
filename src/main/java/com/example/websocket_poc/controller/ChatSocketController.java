package com.example.websocket_poc.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.example.websocket_poc.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ChatSocketController {

    @Autowired
    private SocketIOServer server;

    @PostConstruct
    public void receiveMessage() {
        server.addEventListener("join", String.class, this::handleJoin);
        server.addEventListener("message", ChatMessage.class, this::handleChat);
    }

    private void handleJoin(SocketIOClient client, String room, AckRequest ackSender) {
        client.joinRoom(room);
    }

    private void handleChat(SocketIOClient client, ChatMessage data, AckRequest ackSender) {
        server.getRoomOperations("messages").sendEvent("chat", data);
    }
}
