package com.example.online_shop.dto.authen;

import com.example.online_shop.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginResponse {
    private Long id;
    private String email;
    private String name;
    private String token;
}
