package com.sbear.org.authenticationservice;

import com.sbear.org.authenticationservice.entity.UserInfo;
import com.sbear.org.authenticationservice.repository.UserInfoRepository;
import com.sbear.org.authenticationservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserInfoRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser_UserNotExists() {

        UserInfo userInfo = new UserInfo();
        userInfo.setName("John Doe");
        userInfo.setEmail("john.doe@example.com");
        userInfo.setPassword("password");
        userInfo.setRoles("USER");

        when(repository.findByEmailAndName(any(String.class), any(String.class))).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");


        String result = userService.addUser(userInfo);


        assertEquals("user added to system ", result);

        UserInfo savedUser = new UserInfo();
        savedUser.setName("John Doe");
        savedUser.setEmail("john.doe@example.com");
        savedUser.setPassword("encodedPassword");
        savedUser.setRoles("USER");

        verify(repository).save(savedUser);
    }

    @Test
    void testAddUser_UserExists() {
        // Arrange
        UserInfo userInfo = new UserInfo();
        userInfo.setName("John Doe");
        userInfo.setEmail("john.doe@example.com");
        userInfo.setPassword("password");
        userInfo.setRoles("USER");

        when(repository.findByEmailAndName(any(String.class), any(String.class))).thenReturn(Optional.of(userInfo));


        String result = userService.addUser(userInfo);


        assertEquals("User with this email and name already exists.", result);
        verify(repository, never()).save(any(UserInfo.class));
    }
}
