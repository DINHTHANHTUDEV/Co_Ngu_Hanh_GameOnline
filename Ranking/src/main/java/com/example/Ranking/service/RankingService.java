package com.example.Ranking.service;

import com.example.Ranking.DTO.RankingDTO;
import com.example.Ranking.entity.MatchingHistory;
import com.example.Ranking.entity.Ranking;
import com.example.Ranking.entity.User;
import com.example.Ranking.repository.MatchingHistoryRepository;
import com.example.Ranking.repository.RankingRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {
    @Autowired
    private RankingRepo rankingRepo;

    @Autowired
    private MatchingHistoryRepository matchingHistoryRepo;

    //hien thi top 10 theo thang
    public List<RankingDTO> getTop10ByMonthAndYear(int month, int year) {
        Pageable top10 = PageRequest.of(0, 10); // Top 10
        return rankingRepo.findTop10ByMonthAndYear(month, year, top10);
    }

    // Tính điểm xếp hạng cho tháng hiện tại
    public void calculateRankingForCurrentMonth(int month, int year) {
        List<MatchingHistory> matchHistories = matchingHistoryRepo.findMatchesByMonthAndYear(month, year);

        // Duyệt qua tất cả các trận đấu và tính điểm
        for (MatchingHistory match : matchHistories) {
            // Lấy người chơi từ trận đấu
            User player1 = match.getPlayer1();
            User player2 = match.getPlayer2();
            User winner = match.getWinner();

            // Tính điểm cho người chơi dựa trên kết quả
            int player1Points = calculateMatchPoints(winner, player1);
            int player2Points = calculateMatchPoints(winner, player2);

            // Cập nhật bảng xếp hạng cho cả hai người chơi
            updateRanking(player1, player1Points, month, year);
            updateRanking(player2, player2Points, month, year);
        }
    }

    // Hàm tính điểm từ trận đấu (có thể thay đổi theo quy tắc của bạn)
    private int calculateMatchPoints(User winner, User player) {
        if (winner != null) {
            if (winner.equals(player)) {
                return 3; // Thắng trận
            }
        }
        return 0; // Thua trận hoặc hòa (tùy vào cách tính điểm của bạn)
    }

    // Cập nhật hoặc tạo mới bảng xếp hạng cho người chơi
    private void updateRanking(User player, int points, int month, int year) {
        Ranking existingRanking = rankingRepo.findByUserAndMonthAndYear(player, month, year);
        if (existingRanking != null) {
            existingRanking.setRank_points(existingRanking.getRank_points() + points); // Cộng điểm vào hiện có
            rankingRepo.save(existingRanking);
        } else {
            Ranking newRanking = new Ranking();
            newRanking.setUser(player);
            newRanking.setRank_points(points);
            newRanking.setMonth(month);
            newRanking.setYear(year);
            rankingRepo.save(newRanking); // Lưu vào bảng xếp hạng
        }
    }

}
