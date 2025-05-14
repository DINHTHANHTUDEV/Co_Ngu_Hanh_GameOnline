package com.example.co_ngu_hanh_game.repository;

import com.example.co_ngu_hanh_game.entity.PlayerProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerProfileRepository extends JpaRepository<PlayerProfiles, Integer> {
    Optional<PlayerProfiles> findTopByOrderByWinsDesc();
    List<PlayerProfiles> findTop10ByOrderByEloPointsDesc();
    List<PlayerProfiles> findTop10ByOrderByCurrentStarsDesc();
    List<PlayerProfiles> findTop10ByOrderByWinrateDesc();
}
