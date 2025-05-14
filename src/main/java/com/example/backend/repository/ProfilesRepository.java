package com.example.backend.repository;

import com.example.backend.DTO.ProfilesResponse;
import com.example.backend.moder.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilesRepository extends JpaRepository<Profiles,Integer> {
    @Query("""
            select new com.example.backend.DTO.ProfilesResponse (
            pf.user_id,
            pf.total_matches,
            pf.wins,
            pf.winrate,
            pf.current_stars,
            pf.elo_points,
            pf.image_user,
            pf.level_id.level_id,
            pf.level_id.level_name,
            pf.level_id.required_matches,
            pf.level_id.required_winrate,
            pf.level_id.reward_stars,
            pf.level_id.stars_bet
            
            ) from Profiles pf
            """)
    public List<ProfilesResponse> getAllProfiles();
}
