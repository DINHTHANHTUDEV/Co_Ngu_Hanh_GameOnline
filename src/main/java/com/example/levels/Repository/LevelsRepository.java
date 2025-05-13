package com.example.levels.Repository;

import com.example.levels.DTO.LevelsDTO;
import com.example.levels.Entity.Levels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface LevelsRepository extends JpaRepository<Levels, Integer> {
    @Query("""
            SELECT  new com.example.levels.DTO.LevelsDTO(
            lv.level_id,lv.level_name,lv.required_matches,lv.required_winrate,lv.reward_stars,lv.stars_bet
            )FROM Levels lv
            """)
    List<LevelsDTO> HienThiLevels();

    @Query("""
            SELECT  new com.example.levels.DTO.LevelsDTO(
            lv.level_id,lv.level_name,lv.required_matches,lv.required_winrate,lv.reward_stars,lv.stars_bet
            )FROM Levels lv WHERE lv.level_id=?1
            """)
    LevelsDTO DetailLevels_ID(Integer id);
}
