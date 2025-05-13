package com.example.Ranking.repository;

import com.example.Ranking.DTO.RankingDTO;
import com.example.Ranking.entity.Ranking;
import com.example.Ranking.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
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

    // Tìm bảng xếp hạng cho người chơi trong tháng và năm cụ thể
    Ranking findByUserAndMonthAndYear(User player, int month, int year);
}
