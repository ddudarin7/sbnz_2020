package com.ftn.sbnz_2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
	
    private final SimpMessagingTemplate template;

    @Autowired
    NotificationController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/send/message")
    public void sendMessage(String message){
        System.out.println(message);
        this.template.convertAndSend("/message",  message);
    }

}
