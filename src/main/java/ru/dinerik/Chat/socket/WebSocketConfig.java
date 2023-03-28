package ru.dinerik.Chat.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// Настраиваем конечную точку STOMP и брокер сообщений
@Configuration
@EnableWebSocketMessageBroker           // Включает Websocket сервер
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Настраиваем URL, по которому клиентское приложение в браузере будет обращаться
    // для перехода к обмену данными через WebSocket
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-connect")
                .withSockJS()
                .setClientLibraryUrl("/webjars/sockjs-client/1.5.1/sockjs.min.js");
    }

    // Настраиваем брокер сообщений для использования протокола STOMP
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Входящие сообщения
        registry.setApplicationDestinationPrefixes("/chat_in");
        // Исходящие сообщения
        registry.enableSimpleBroker("/chat_out");
    }
}