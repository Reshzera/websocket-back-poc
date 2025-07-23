package com.example.websocket_poc.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import com.corundumstudio.socketio.AuthorizationResult;


@org.springframework.context.annotation.Configuration
public class SocketIOConfig {

    private SocketIOServer server;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(8081);

        config.setAuthorizationListener(handshakeData -> {
            String token = handshakeData.getSingleUrlParam("token");

            if (token == null || !isValidToken(token)) {
                return AuthorizationResult.FAILED_AUTHORIZATION;
            }
            return AuthorizationResult.SUCCESSFUL_AUTHORIZATION;
        });

        server = new SocketIOServer(config);
        server.start();
        System.out.println("🚀 Socket.IO server started on port 8081");
        return server;
    }

    @PreDestroy
    public void stop() {
        if (server != null) {
            server.stop();
            System.out.println("🛑 Socket.IO server stopped");
        }
    }

    private boolean isValidToken(String token) {
        return token.equals("token-auth");
    }
}
