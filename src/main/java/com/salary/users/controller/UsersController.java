package com.salary.users.controller;

import com.salary.users.entity.Users;
import com.salary.users.dto.UserInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/*")
@RestController
public class UsersController {

    @GetMapping("/{user-identifier}")
    public ResponseEntity<Void> sendFriendRequest(@PathVariable("user-identifier") String userIdentifier){
        return null;
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> getUserInfo(@AuthenticationPrincipal Users users){
        return new ResponseEntity<>(UserInfoDto.toDto(users), HttpStatus.OK);
    }
}
