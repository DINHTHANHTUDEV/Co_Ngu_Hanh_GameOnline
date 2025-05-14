package com.example.co_ngu_hanh_game.controller;

import com.example.co_ngu_hanh_game.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        return ResponseEntity.ok(adminService.getAdminSummary());
    }

    @GetMapping("/top-players")
    public ResponseEntity<?> getTopPlayers() {
        return ResponseEntity.ok(adminService.getTopPlayers());
    }
}
