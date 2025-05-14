package com.example.co_ngu_hanh_game.service;

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

    public Map<String, Object> getAdminSummary() {
        long onlineUsers = userRepository.countByIsOnlineTrue();
        long totalMatches = profileRepository.findAll()
                .stream()
                .mapToLong(PlayerProfiles::getTotalMatches)
                .sum();
        PlayerProfiles topWinner = profileRepository.findTopByOrderByWinsDesc().orElse(null);

        Map<String, Object> result = new HashMap<>();
        result.put("totalMatches", totalMatches);
        result.put("onlineUsers", onlineUsers);
        result.put("topWinner", topWinner != null ? topWinner.getUser().getUsername() : null);
        return result;
    }

    public Map<String, List<PlayerProfiles>> getTopPlayers() {
        Map<String, List<PlayerProfiles>> map = new HashMap<>();
        map.put("topElo", profileRepository.findTop10ByOrderByEloPointsDesc());
        map.put("topStars", profileRepository.findTop10ByOrderByCurrentStarsDesc());
        map.put("topWinrate", profileRepository.findTop10ByOrderByWinrateDesc());
        return map;
    }
}
