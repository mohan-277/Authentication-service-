package com.sbear.org.authenticationservice.service;

import com.sbear.org.authenticationservice.entity.UserInfo;
import com.sbear.org.authenticationservice.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserInfoRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        Optional<UserInfo> savedUser = repository.findByEmailAndName(userInfo.getEmail(),userInfo.getName());
        if(savedUser.isPresent()){
            return "User with this email and name already exists.";
        }else {
            repository.save(userInfo);
            return "user added to system ";
        }
    }
}
