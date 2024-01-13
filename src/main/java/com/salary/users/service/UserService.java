package com.salary.users.service;

import com.salary.users.entity.Users;
import com.salary.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Users getUser(String userIdentifier){
        return null;
    }

    public Users getUserInfo(String sub){
        return userRepository.findBySub(sub).orElse(null);
    }
}
