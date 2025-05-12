package com.example.matching_history.Controlller;

import com.example.matching_history.DTO.MatchHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MatchHistoryWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendMatchHistory(MatchHistoryDTO dto) {
        messagingTemplate.convertAndSend("/topic/match-history", dto);
    }
}
