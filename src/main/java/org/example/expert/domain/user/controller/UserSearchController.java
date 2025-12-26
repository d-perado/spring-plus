package org.example.expert.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.service.UserSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSearchController {

    private final UserSearchService userSearchService;

    @GetMapping("/search/user")
    public ResponseEntity<UserResponse> search(@RequestParam String nickname){
        UserResponse result = userSearchService.searchUser(nickname);
        return ResponseEntity.ok(result);
    }
}
