package com.sbear.org.authenticationservice;


import com.sbear.org.authenticationservice.config.UserInfoUserDetailsService;
import com.sbear.org.authenticationservice.entity.UserInfo;
import com.sbear.org.authenticationservice.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserInfoUserDetailsServiceTest {

    @Mock
    private UserInfoRepository repository;

    @InjectMocks
    private UserInfoUserDetailsService userInfoUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsernameSuccess() {
        // Arrange
        String username = "testuser";
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setPassword("password");
        userInfo.setRoles("ROLE_USER");
        when(repository.findByName(username)).thenReturn(Optional.of(userInfo));

        // Act
        UserDetails userDetails = userInfoUserDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void testLoadUserByUsernameUserNotFound() {
        // Arrange
        String username = "nonexistentuser";
        when(repository.findByName(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userInfoUserDetailsService.loadUserByUsername(username));
    }
}
