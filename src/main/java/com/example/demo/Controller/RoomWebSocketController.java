package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RoomWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendRoomUpdate(String message) {
        // Gửi đến client đã đăng ký topic này
        messagingTemplate.convertAndSend("/topic/room-updates", message);
    }
}
