package com.example.Ranking.service;

import com.example.Ranking.DTO.RankingDTO;
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

    public List<RankingDTO> getTop10ByMonthAndYear(int month, int year) {
        Pageable top10 = PageRequest.of(0, 10); // Top 10
        return rankingRepo.findTop10ByMonthAndYear(month, year, top10);
    }

}
