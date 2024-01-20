package com.example.online_shop.dto.user;


import com.example.online_shop.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    private String email;
    private String name;
    private String password;
    private Integer age;
}
