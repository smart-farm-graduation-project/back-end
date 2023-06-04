package com.farm.smartfarm.socektConnect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final SimpMessagingTemplate template;

//    @GetMapping("/chat")
//    public String chatGET(){
//
//        log.info("@ChatController, chat GET()");
//
//        return "chat";
//    }
    @MessageMapping(value = "/chat/enter")
    public void enter(Message message){
        log.info("enter user + " + message.toString());
        message.setMessage(message.getSender() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(Message message){
        log.info(message.toString());
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message.toString());
    }

    @GetMapping("/send-message")
    public String sendMessage(@RequestParam String roomId,
                              @RequestParam String sender,
                              @RequestParam String message) {
        // HTTP GET 요청 처리 로직
//       if try fail parameter = Message msg
//        Message message = "Hello, WebSocket!";
        log.info("message - " + roomId);
        Message msg = Message.builder()
                        .roomId(roomId)
                                .sender(sender)
                                        .message(message)
                                                .build();
        message(msg); // WebSocket 메시지 처리 메소드 호출
        return "Message sent";
    }

}
