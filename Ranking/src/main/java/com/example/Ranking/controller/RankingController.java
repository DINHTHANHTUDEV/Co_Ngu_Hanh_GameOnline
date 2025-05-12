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

}
