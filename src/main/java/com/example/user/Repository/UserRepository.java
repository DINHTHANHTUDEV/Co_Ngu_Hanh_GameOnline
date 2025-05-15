package com.example.user.Repository;

import com.example.user.DTO.UserDTO;
import com.example.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("""
            SELECT new com.example.user.DTO.UserDTO(
            us.user_id,us.username,us.is_online,us.last_online,us.last_offline
            )FROM User  us
            """)
    List<UserDTO> hienThiUser();

    @Query("""
    SELECT new com.example.user.DTO.UserDTO(
        us.user_id, us.username, us.is_online, us.last_online, us.last_offline
    ) FROM User us WHERE us.is_online = 1
""")
    List<UserDTO> hienThiUserOnline();

    @Query("""
    SELECT new com.example.user.DTO.UserDTO(
        us.user_id, us.username, us.is_online, us.last_online, us.last_offline
    ) FROM User us WHERE us.username like %?1%
""")
    List<UserDTO> findByUsername(String username);
}
