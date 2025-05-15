package com.example.co_ngu_hanh_game.controller;

import com.example.co_ngu_hanh_game.dto.PlayerProfileDTO;
import com.example.co_ngu_hanh_game.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminWebSocketController adminWebSocketController;

    //thong ke người thắng nhiều nhất
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        return ResponseEntity.ok(adminService.getAdminSummary());
    }

    //Top người chơi có elo/stars/winrate cao nhất
    @GetMapping("/top-players")
    public ResponseEntity<?> getTopPlayers() {
        return ResponseEntity.ok(adminService.getTopPlayers());
    }

    // ✅ Gửi WebSocket test
    @PostMapping("/test-ws")
    public ResponseEntity<String> testSendWebSocket() {
        PlayerProfileDTO summary = adminService.getAdminSummary();
        adminWebSocketController.sendAdminSummary(summary);
        return ResponseEntity.ok("Đã gửi admin summary qua WebSocket");
    }
}
