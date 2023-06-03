package com.farm.smartfarm.socektConnect;

import lombok.*;

@Getter
@Builder
@Setter
public class Message {

//    private String type;
    private String roomId;
    private String sender;
    private String message;

//    public void setSender(String sender) {
//        this.sender = sender;
//    }
//
//    public void newConnect() {
//        this.type = "new";
//    }
//
//    public void closeConnect() {
//        this.type = "close";
//    }
}
