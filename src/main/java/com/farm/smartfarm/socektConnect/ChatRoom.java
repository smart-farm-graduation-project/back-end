package com.farm.smartfarm.socektConnect;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

//    @Builder
//    public ChatRoom(String roomId, String name) {
//        this.roomId = roomId;
//        this.name = name;
//    }

    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();

        room.roomId = name;
        room.name = name;
        return room;
    }

//    public void handlerActions(WebSocketSession session, Message message, ChatService chatService) {
//        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
//            sessions.add(session);
//            log.info(chatMessage.getSender() + " enter");
//        }
//        sendMessage(chatMessage, chatService);
//    }
    /*private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }*/


}
