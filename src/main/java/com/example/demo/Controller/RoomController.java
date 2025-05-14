package com.example.demo.Controller;

import com.example.demo.DTO.Request.CreateRoomRequest;
import com.example.demo.DTO.Response.RoomPublicResponse;
import com.example.demo.Entity.Room;
import com.example.demo.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // tạo phòng
    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomRequest request) {
        try {
            Room room = roomService.createRoom(request);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // list phòng công khai (0 là công khai)
    @GetMapping("/public")
    public List<RoomPublicResponse> getPublicRooms() {
        return roomService.getPublicRooms();
    }

    // tham gia phòng
    @PostMapping("/{roomId}/join")
    public ResponseEntity<?> joinRoom(@PathVariable Integer roomId, @RequestParam Integer userId) {
        return ResponseEntity.ok(roomService.joinRoom(roomId, userId));
    }

    // rời phòng
    @DeleteMapping("/{roomId}/leave")
    public ResponseEntity<?> leaveRoom(@PathVariable Integer roomId, @RequestParam Integer userId) {
        return ResponseEntity.ok(roomService.leaveRoom(roomId, userId));
    }

    // chi tiết phòng
    @GetMapping("/detail/{roomId}")
    public ResponseEntity<?> getRoomDetails(@PathVariable Integer roomId) {
        return ResponseEntity.ok(roomService.getRoomDetails(roomId));
    }

    // xóa phòng
    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Integer roomId) {
        return ResponseEntity.ok(roomService.deleteRoom(roomId));
    }

    // bắt đầu trận
    @PostMapping("/{roomId}/start")
    public ResponseEntity<?> startRoom(@PathVariable Integer roomId) {
        return ResponseEntity.ok(roomService.startRoom(roomId));
    }

    // list người trong phòng
    @GetMapping("/members/{roomId}")
    public ResponseEntity<?> getRoomMembers(@PathVariable Integer roomId) {
        return ResponseEntity.ok(roomService.getRoomMembers(roomId));
    }
}
