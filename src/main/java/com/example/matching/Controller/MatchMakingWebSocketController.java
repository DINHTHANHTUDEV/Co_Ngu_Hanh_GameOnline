package com.example.matching.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MatchMakingWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendQueueUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/matchmaking-updates", message);
    }
}
