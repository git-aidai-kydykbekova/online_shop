package com.example.online_shop.service;

import com.example.online_shop.dto.user.UserRegisterRequest;
import com.example.online_shop.dto.authen.AuthLoginRequest;
import com.example.online_shop.dto.authen.AuthLoginResponse;
import com.example.online_shop.entities.User;

public interface AuthService {
    void register(UserRegisterRequest userRegisterRequest);

    AuthLoginResponse login(AuthLoginRequest authLoginRequest);

    User getUsernameFromToken(String token);
}
