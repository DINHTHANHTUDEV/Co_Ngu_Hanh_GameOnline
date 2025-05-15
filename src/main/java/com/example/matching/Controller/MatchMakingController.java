package com.example.matching.Controller;

import com.example.matching.DTO.MatchMakingDTO;
import com.example.matching.Repository.MatchMakingRepository;
import com.example.matching.Service.MatchMakingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matchmaking")
public class MatchMakingController {

    @Autowired
    private MatchMakingRepository matchMakingRepository;

    @Autowired
    private MatchMakingService matchMakingService;

    @Autowired
    private MatchMakingWebSocketController matchMakingWebSocketController;

    // API vào hàng chờ
    @PostMapping("/join")
    public ResponseEntity<String> joinMatchMaking(@Valid @RequestBody MatchMakingDTO matchMakingDTO) {
        try {
            matchMakingService.joinQueue(matchMakingDTO);
            // Gửi thông báo realtime qua WebSocket
            matchMakingWebSocketController.sendQueueUpdate("User " + matchMakingDTO.getUserId() + " đã vào hàng chờ.");
            return ResponseEntity.ok("Tham gia hàng chờ ghép trận thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tham gia hàng chờ thất bại: " + e.getMessage());
        }
    }

    // API thoát hàng chờ
    @PostMapping("/leave")
    public ResponseEntity<String> leaveMatchMaking(@Valid @RequestBody MatchMakingDTO matchMakingDTO) {
        try {
            matchMakingService.leaveQueue(matchMakingDTO.getUserId());
            // Gửi thông báo realtime qua WebSocket
            matchMakingWebSocketController.sendQueueUpdate("User " + matchMakingDTO.getUserId() + " đã rời hàng chờ.");
            return ResponseEntity.ok("Rời hàng chờ ghép trận thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Kiểm tra trạng thái hàng chờ
    @GetMapping("/status/{userId}")
    public ResponseEntity<?> checkQueueStatus(@PathVariable Integer userId) {
        try {
            boolean inQueue = matchMakingService.isUserInQueue(userId);
            if (inQueue) {
                return ResponseEntity.ok("Người dùng đang trong hàng chờ ghép trận");
            } else {
                return ResponseEntity.ok("Người dùng không có trong hàng chờ");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
        }
    }
}
