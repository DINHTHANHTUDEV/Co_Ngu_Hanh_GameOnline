package com.example.Ranking.repository;

import com.example.Ranking.entity.MatchingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MatchingHistoryRepository extends JpaRepository<MatchingHistory, Integer> {
    @Query("SELECT m FROM MatchingHistory m WHERE MONTH(m.played_at) = :month AND YEAR(m.played_at) = :year")
    List<MatchingHistory> findMatchesByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
