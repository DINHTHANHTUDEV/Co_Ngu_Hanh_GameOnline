package com.example.matching.Repository;

import com.example.matching.Entity.MatchMaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchMakingRepository extends JpaRepository<MatchMaking, Integer> {
    @Query("SELECT m FROM MatchMaking m WHERE m.user.userId = :userId")
    Optional<MatchMaking> findByUserId(@Param("userId") Integer userId);


}
