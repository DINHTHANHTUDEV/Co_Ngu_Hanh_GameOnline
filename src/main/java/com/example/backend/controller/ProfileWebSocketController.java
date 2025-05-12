package com.example.backend.controller;

import com.example.backend.DTO.ProfilesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ProfileWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendProfileToClient(ProfilesResponse profile) {
        messagingTemplate.convertAndSend("/topic/profile", profile);
    }
}
