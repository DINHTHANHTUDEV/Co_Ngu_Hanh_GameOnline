package com.example.levels.Controller;

import com.example.levels.DTO.LevelsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LevelsWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Gửi 1 levels cụ thể
    public void sendLevel(LevelsDTO level) {
        messagingTemplate.convertAndSend("/topic/levels", level);
    }

    // Gửi toàn bộ danh sách levels
    public void sendAllLevels(List<LevelsDTO> levelsList) {
        messagingTemplate.convertAndSend("/topic/levels", levelsList);
    }
}
