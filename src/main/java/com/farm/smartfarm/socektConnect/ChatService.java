package com.farm.smartfarm.socektConnect;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatService {
//    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;
//    private final SimpMessageSendingOperations simpMessageSendingOperations;

//    /*
//    * /sub/roomId/{roomId} : 구독
//    * /pub/hello           : send message
//    *
//    */
//
//    @MessageMapping("/chat")
//    public void message(ChatMessage message) {
//        log.info("message");
//        simpMessageSendingOperations.convertAndSend("/queue/roomId/" + message.getRoomId(), message);
//    }
    @PostConstruct
    private void init() {
        chatRooms = new TreeMap<>();
    }
//
//    public List<ChatRoom> findAllRoom() {
//        return new ArrayList<>(chatRooms.values());
//    }
//
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }
//
    public ChatRoom createRoom(String farmNum) {
        log.info("enter user");
//        String randomId = UUID.randomUUID().toString();
        ChatRoom room = ChatRoom.create(farmNum);
        chatRooms.put(room.getRoomId(), room);

        return room;
//        return chatRoom;
    }
//
//    public <T> void sendMessage(WebSocketSession session, T message){
//        try {
//            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }

}
