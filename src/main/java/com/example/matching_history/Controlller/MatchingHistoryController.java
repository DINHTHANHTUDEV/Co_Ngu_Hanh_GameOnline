package com.example.matching_history.Controlller;

import com.example.matching_history.DTO.MatchHistoryDTO;
import com.example.matching_history.Repository.MatchingHistoryRepository;
import com.example.matching_history.Service.MatchingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class MatchingHistoryController {
    @Autowired
    private MatchingHistoryRepository matchingHistoryRepository;

    @GetMapping("/api/matchingHistory")
    public List<MatchHistoryDTO> listMatchingHistory() {
        return matchingHistoryRepository.findAllMatchHistoryDTO();
    }

    @Autowired
    private MatchHistoryWebSocketController wsController;

    @PostMapping("/test")
    public String testSendMatchHistory(@RequestBody MatchHistoryDTO dto) {
        wsController.sendMatchHistory(dto);
        return "Đã gửi match history qua WebSocket";
    }
}
