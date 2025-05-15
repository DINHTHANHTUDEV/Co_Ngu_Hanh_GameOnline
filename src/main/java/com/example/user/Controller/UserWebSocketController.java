package com.example.user.Controller;

import com.example.user.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class UserWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendUserData(UserDTO dto) {
        messagingTemplate.convertAndSend("/topic/user", dto);
    }
}
