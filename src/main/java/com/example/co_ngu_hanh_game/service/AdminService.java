package com.example.co_ngu_hanh_game.service;

import com.example.co_ngu_hanh_game.dto.PlayerProfileDTO;
import com.example.co_ngu_hanh_game.entity.PlayerProfiles;
import com.example.co_ngu_hanh_game.repository.PlayerProfileRepository;
import com.example.co_ngu_hanh_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlayerProfileRepository profileRepository;

    // ✅ Trả về PlayerProfileDTO (top người thắng nhiều nhất)
    public PlayerProfileDTO getAdminSummary() {
        PlayerProfiles topWinner = profileRepository.findTopByOrderByWinsDesc().orElse(null);

        if (topWinner == null) {
            return new PlayerProfileDTO(); // Trả về DTO rỗng nếu không có
        }

        return new PlayerProfileDTO(
                topWinner.getUser().getUsername(),
                topWinner.getWins(),
                topWinner.getWinrate(),
                topWinner.getEloPoints(),
                topWinner.getCurrentStars()
        );
    }

    public Map<String, List<PlayerProfiles>> getTopPlayers() {
        Map<String, List<PlayerProfiles>> map = new HashMap<>();
        map.put("topElo", profileRepository.findTop10ByOrderByEloPointsDesc());
        map.put("topStars", profileRepository.findTop10ByOrderByCurrentStarsDesc());
        map.put("topWinrate", profileRepository.findTop10ByOrderByWinrateDesc());
        return map;
    }
}
