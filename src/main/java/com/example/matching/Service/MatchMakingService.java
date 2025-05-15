package com.example.matching.Service;

import com.example.matching.DTO.MatchMakingDTO;
import com.example.matching.Entity.MatchMaking;
import com.example.matching.Entity.User;
import com.example.matching.Repository.MatchMakingRepository;
import com.example.matching.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MatchMakingService {
    @Autowired
    private MatchMakingRepository matchMakingRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void joinQueue(MatchMakingDTO dto) {
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("Mã người dùng là bắt buộc");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với mã " + dto.getUserId()));

        MatchMaking matchMaking = new MatchMaking();
        matchMaking.setUser(user);
        matchMaking.setEntered_at(dto.getEntered_at() != null ? dto.getEntered_at() : LocalDateTime.now());

        matchMakingRepository.save(matchMaking);
    }

    @Transactional
    public void leaveQueue(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Mã người dùng là bắt buộc");
        }

        MatchMaking matchMaking = matchMakingRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bản ghi ghép trận cho người dùng với mã " + userId));

        matchMakingRepository.delete(matchMaking);
    }

//    Kiểm tra trạng thái hàng chờ
    public boolean isUserInQueue(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Mã người dùng là bắt buộc");
        }

        return matchMakingRepository.findByUserId(userId).isPresent();
    }

}
