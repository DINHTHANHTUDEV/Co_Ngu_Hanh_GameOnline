package com.example.demo.Service;

import com.example.demo.DTO.Request.CreateRoomRequest;
import com.example.demo.Entity.Room;
import com.example.demo.Entity.User;
import com.example.demo.Repository.RoomRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public Room createRoom(CreateRoomRequest request) throws Exception {
        Optional<User> userOpt = userRepository.findById(request.getCreatedByUserId());
        if (userOpt.isEmpty()) {
            throw new Exception("Người tạo phòng không tồn tại");
        }

        String roomCode;
        do {
            roomCode = generateRoomCode();
        } while (roomRepository.existsByRoomCode(roomCode));

        Room room = new Room();
        room.setRoomCode(roomCode);
        room.setCreatedBy(userOpt.get());
        room.setElo_points(request.getEloPoints());
        room.setIs_private(request.getIsPrivate());
        room.setPassword(request.getIsPrivate() ? request.getPassword() : null);

        return roomRepository.save(room);
    }

    private String generateRoomCode() {
        return "ROOM" + ThreadLocalRandom.current().nextInt(100, 999);
    }
}
