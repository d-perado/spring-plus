package org.example.expert.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSearchService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, UserResponse> redisTemplate;
    private static final String key = "user:search:";

    public UserResponse searchUser(String nickname) {

        UserResponse cached = redisTemplate.opsForValue().get(key + nickname);

        if (cached != null) {
            log.info("레디스 접근");
            return cached;
        }

        Optional<User> user = userRepository.findByNicknameEquals(nickname);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("유저 존재 x");
        }

        User findedUser = user.get();

        UserResponse response = new UserResponse(findedUser.getId(), findedUser.getEmail());

        redisTemplate.opsForValue().set(key + nickname, response, Duration.ofMinutes(5));

        return response;

    }
}
