package com.example.online_shop.dto.phone;

import com.example.online_shop.enums.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneAddRequest {


    private String name;
    private Integer price;
    private String country;
    private String serialNumber;
    private Integer ram;
}
