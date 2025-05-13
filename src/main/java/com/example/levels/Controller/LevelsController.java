package com.example.levels.Controller;

import com.example.levels.DTO.LevelsDTO;
import com.example.levels.Repository.LevelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api/levels")
public class LevelsController {
    @Autowired
    private LevelsRepository levelsRepository;

    @Autowired
    private LevelsWebSocketController wsController;

    @GetMapping("/hienThi")
    public List<LevelsDTO> hienThi(){
        return levelsRepository.HienThiLevels();
    }

    @GetMapping("/{id}")
    public LevelsDTO findById(@PathVariable int id){
        return levelsRepository.DetailLevels_ID(id);
    }

    // Gửi 1 levels cụ thể qua WebSocket để test
    @PostMapping("/test/send")
    public String sendLevelToWebSocket(@RequestBody LevelsDTO level) {
        wsController.sendLevel(level);
        return "Đã gửi level qua WebSocket";
    }

    // Gửi toàn bộ danh sách levels qua WebSocket để test
    @PostMapping("/test/sendAll")
    public String sendAllLevelsToWebSocket() {
        List<LevelsDTO> allLevels = levelsRepository.HienThiLevels();
        wsController.sendAllLevels(allLevels);
        return "Đã gửi toàn bộ levels qua WebSocket";
    }
}
