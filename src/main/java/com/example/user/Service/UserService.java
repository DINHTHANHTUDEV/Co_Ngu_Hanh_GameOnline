package com.example.user.Service;

import com.example.user.DTO.UserDTO;
import com.example.user.Entity.User;
import com.example.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // cap nhat trang thai online/offline
    @Transactional
    public UserDTO updateOnlineStatus(Integer user_id) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Không tìm thấy người dùng với ID: " + user_id);
        }

        User user = userOptional.get();
        // Đảo trạng thái: 1 -> 0, 0 -> 1
        Integer currentStatus = user.getIs_online() != null ? user.getIs_online() : 0;
        Integer newStatus = currentStatus == 1 ? 0 : 1;
        user.setIs_online(newStatus);

        // Cập nhật last_online hoặc last_offline
        if (newStatus == 1) {
            user.setLast_online(LocalDateTime.now());
            user.setLast_offline(null);
        } else {
            user.setLast_offline(LocalDateTime.now());
            user.setLast_online(null);
        }

        User updatedUser = userRepository.save(user);

        // Ánh xạ sang UserDTO
        return new UserDTO(
                updatedUser.getUser_id(),
                updatedUser.getUsername(),
                updatedUser.getIs_online(),
                updatedUser.getLast_online(),
                updatedUser.getLast_offline()
        );
    }



}
