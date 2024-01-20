package com.example.online_shop.dto.phone;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PhoneResponse {

    private Long id;
    private String name;
    private Integer price;
    private String country;
    private String serialNumber;
    private Integer ram;
    private Boolean isExist;
    private String date;
}
