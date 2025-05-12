package com.example.matching_history.Repository;

import com.example.matching_history.DTO.MatchHistoryDTO;
import com.example.matching_history.Entity.MatchingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingHistoryRepository extends JpaRepository<MatchingHistory,Integer> {
    @Query("""
    SELECT new com.example.matching_history.DTO.MatchHistoryDTO(
        mH.player1.username,
        mH.player2.username,
        mH.winner.username,
        mH.match_type,
        mH.stars_bet,
        mH.stars_change,
        mH.elo_change,
        mH.moves_json,
        mH.played_at
    )
    FROM MatchingHistory mH
""")
    List<MatchHistoryDTO> findAllMatchHistoryDTO();
}
