package com.example.Ranking.repository;

import com.example.Ranking.DTO.RankingDTO;
import com.example.Ranking.entity.Ranking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RankingRepo extends JpaRepository<Ranking,Integer> {
    @Query("""
    SELECT 
        new com.example.Ranking.DTO.RankingDTO(
            rk.user.user_id,
            rk.user.username,
            rk.rank_points,
            rk.month,
            rk.year
        )
    FROM Ranking rk
    WHERE rk.month = :month AND rk.year = :year
    ORDER BY rk.rank_points DESC
""")
    List<RankingDTO> findTop10ByMonthAndYear(@Param("month") Integer month,
                                             @Param("year") Integer year,
                                             Pageable pageable);

}
