package com.example.Ranking.controller;

import com.example.Ranking.DTO.RankingDTO;
import com.example.Ranking.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/Ranking")
public class RankingController {
    @Autowired
    private RankingService rankingService;

    @Autowired
    private RankingWebSocketController wsController;

    // top 10 theo thang
    @GetMapping("/top10")
    public ResponseEntity<List<RankingDTO>> getTop10Rankings(
            @RequestParam("month") int month,
            @RequestParam("year") int year) {

        List<RankingDTO> topRankings = rankingService.getTop10ByMonthAndYear(month, year);
        return ResponseEntity.ok(topRankings);
    }

    @PostMapping("/WebSocket")
    public String pushRanking(@RequestBody RankingDTO dto) {
        wsController.sendRanking(dto);
        return "Ranking da gui toi WebSocket!";
    }

    // Endpoint để cập nhật bảng xếp hạng tháng từ match history
    @PostMapping("/update")
    public String updateMonthlyRanking(@RequestParam int month, @RequestParam int year) {
        try {
            // Gọi service để tính toán và cập nhật bảng xếp hạng
            rankingService.calculateRankingForCurrentMonth(month, year);
            return "Bảng xếp hạng đã được cập nhật thành công cho tháng " + month + " năm " + year;
        } catch (Exception e) {
            return "Đã có lỗi xảy ra khi cập nhật bảng xếp hạng: " + e.getMessage();
        }
    }


}
