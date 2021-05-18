package com.yy.yeb.controller;

import com.yy.yeb.entity.Admin;
import com.yy.yeb.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WsController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMessage chatMessage) {
        Admin admin = (Admin) authentication.getPrincipal();
        chatMessage.setFrom(admin.getUsername());
        chatMessage.setFormNickName(admin.getName());
        chatMessage.setDate(LocalDateTime.now());
        template.convertAndSendToUser(chatMessage.getTo(), "/queue/chat", chatMessage);
    }
}
