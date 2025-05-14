package com.example.co_ngu_hanh_game.repository;

import com.example.co_ngu_hanh_game.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    long countByIsOnlineTrue();

}
