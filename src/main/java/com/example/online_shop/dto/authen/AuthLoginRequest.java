package com.example.online_shop.dto.authen;

import com.example.online_shop.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthLoginRequest {
    private String email;
    private String password;
}
