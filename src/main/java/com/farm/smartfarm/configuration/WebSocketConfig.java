package com.farm.smartfarm.configuration;


import com.farm.smartfarm.socektConnect.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
//@EnableWebSocket
public class WebSocketConfig
        implements WebSocketMessageBrokerConfigurer
//        implements WebSocketConfigurer
{
    private final WebSocketHandler webSocketHandler;
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry
//                .addHandler(webSocketHandler, "/ws/chat")
//                .setAllowedOrigins("http://localhost:3000")
//                .withSockJS();
//    }

//    @Bean
//    public org.springframework.web.socket.WebSocketHandler signalingSocketHandler() {
//        return new WebSocketHandler();
//    }
//    implements WebSocketMessageBrokerConfigurer
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
//                .setAllowedOrigins("*")
//                .withSockJS();
    }
}
