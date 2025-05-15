package com.example.co_ngu_hanh_game.controller;

import com.example.co_ngu_hanh_game.dto.PlayerProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class AdminWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendAdminSummary(PlayerProfileDTO summary) {
        messagingTemplate.convertAndSend("/topic/admin-summary", summary);
    }
}
