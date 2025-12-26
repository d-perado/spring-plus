package org.example.expert.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSearchService {

    private final UserRepository userRepository;

    public UserResponse searchUser(String nickname){
        Optional<User> user = userRepository.findByNicknameEquals(nickname);

        if(user.isEmpty()){
            return null;
        }

        User findedUser = user.get();

        return new UserResponse(findedUser.getId(), findedUser.getEmail());
    }
}
