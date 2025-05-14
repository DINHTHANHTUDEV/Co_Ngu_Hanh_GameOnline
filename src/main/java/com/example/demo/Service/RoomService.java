package com.example.demo.Service;

import com.example.demo.DTO.Request.CreateRoomRequest;
import com.example.demo.DTO.Response.RoomPublicResponse;
import com.example.demo.Entity.Room;
import com.example.demo.Entity.User;
import com.example.demo.Repository.RoomRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    // tạo phòng
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
        room.setIsPrivate(request.getIsPrivate());
        room.setPassword(request.getIsPrivate() ? request.getPassword() : null);

        return roomRepository.save(room);
    }

    // gen mã phòng ngẫu nhiên
    private String generateRoomCode() {
        return "ROOM" + ThreadLocalRandom.current().nextInt(100, 999);
    }

    // list phòng công khai
    public List<RoomPublicResponse> getPublicRooms() {
        List<Room> rooms = roomRepository.findPublicRooms();

        return rooms.stream().map(room -> new RoomPublicResponse(
                room.getRoomID(),
                room.getRoomCode(),
                room.getElo_points(),
                room.getCreatedBy().getUsername(),
                room.getCreated_at()
        )).collect(Collectors.toList());
    }

    // tham gia phòng
    public String joinRoom(Integer roomId, Integer userId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        return "Người dùng đã tham gia phòng " + room.getRoomCode();
    }

    // rời phòng
    public String leaveRoom(Integer roomId, Integer userId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại"));
        return "Người dùng đã rời phòng " + room.getRoomCode();
    }

    // chi tiết phòng
    public Room getRoomDetails(Integer roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại"));
    }

    // xóa phòng
    public String deleteRoom(Integer roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại"));
        roomRepository.delete(room);
        return "Đã xoá phòng " + room.getRoomCode();
    }

    // bắt đầu trận
    public String startRoom(Integer roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại"));
        room.setStatus("STARTED");
        roomRepository.save(room);
        return "Trận đã bắt đầu";
    }

    // list người trong phòng
    public List<String> getRoomMembers(Integer roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại"));
        return List.of(room.getCreatedBy().getUsername());
    }
}
