package com.sbear.org.authenticationservice.controller;


import com.sbear.org.authenticationservice.dto.AuthRequest;
import com.sbear.org.authenticationservice.entity.UserInfo;
import com.sbear.org.authenticationservice.service.JwtService;
import com.sbear.org.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class HomeController {


    private final UserService service;


    private final JwtService jwtService;


    private final AuthenticationManager authenticationManager;

    public HomeController(UserService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/signup")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<String> getAllTheTeams() {
        List<String> teams = new ArrayList<>();
        teams.add("RCB");
        teams.add("CSK");
        teams.add("GT");
        return teams;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_PLAYER')")
    public String getPlayerRelated() {
        return "PLayer is login he access his profile";
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        System.out.println("Test");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }

}
