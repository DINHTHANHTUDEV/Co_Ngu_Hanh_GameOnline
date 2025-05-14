package com.example.demo.Repository;

import com.example.demo.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    boolean existsByRoomCode(String roomCode);

    @Query("SELECT r FROM Room r WHERE r.isPrivate = false AND r.status = 'WAITING'")
    List<Room> findPublicWaitingRooms();

    @Query("SELECT r FROM Room r WHERE r.isPrivate = false")
    List<Room> findPublicRooms();
}
