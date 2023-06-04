package com.farm.smartfarm.socektConnect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @GetMapping("/send-message")
    public String sendMessage(Message message) {
        // HTTP GET 요청 처리 로직
//        Message message = "Hello, WebSocket!";
        message(message); // WebSocket 메시지 처리 메소드 호출
        return "Message sent";
    }

}
