package com.example.Ranking.controller;

import com.example.Ranking.DTO.RankingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RankingWebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendRanking(RankingDTO dto) {
        messagingTemplate.convertAndSend("/topic/ranking", dto);
    }
}
