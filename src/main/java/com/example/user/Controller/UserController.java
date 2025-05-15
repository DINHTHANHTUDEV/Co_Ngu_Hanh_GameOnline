package com.example.user.Controller;

import com.example.user.DTO.UserDTO;
import com.example.user.Repository.UserRepository;
import com.example.user.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserWebSocketController userWebSocketController;

    //hien thi tat ca nguoi choi(ca on ca off)
    @GetMapping("/api/hienThiUser")
    public List<UserDTO> hienThiUser() {
        return userRepository.hienThiUser();
    }

    //hien thi tat ca nguoi choi online
    @GetMapping("/api/hienThiUser/Online")
    public List<UserDTO> hienThiUserOnline() {
        return userRepository.hienThiUserOnline();
    }

    //cap nhat trang thai online, offline
    @PutMapping("/{id}/online-status")
    public ResponseEntity<UserDTO> updateOnlineStatus(@PathVariable("id") Integer user_id) {
        UserDTO updatedUser = userService.updateOnlineStatus(user_id);
        return ResponseEntity.ok(updatedUser);
    }

    // tim kiem user theo username
    @GetMapping("/user/search")
    public List<UserDTO> searchUser(@RequestParam String username) {
        return userRepository.findByUsername(username);
    }

    // API test gửi dữ liệu qua WebSocket
    @PostMapping("/user/test-ws")
    public String sendUserViaWebSocket(@RequestBody UserDTO userDTO) {
        userWebSocketController.sendUserData(userDTO);
        return "Đã gửi dữ liệu User qua WebSocket";
    }



}
