package com.example.online_shop.controller;

import com.example.online_shop.dto.user.UserRegisterRequest;
import com.example.online_shop.dto.authen.AuthLoginRequest;
import com.example.online_shop.dto.authen.AuthLoginResponse;
import com.example.online_shop.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody UserRegisterRequest userRegisterRequest) {
        authService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    public AuthLoginResponse login(AuthLoginRequest authLoginRequest) {
        return authService.login(authLoginRequest);
    }

}
