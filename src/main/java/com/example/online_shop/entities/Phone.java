package com.example.online_shop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private String date;
    private Boolean isExist = true;
    private String serialNumber;
    private Integer ram;


    @ManyToOne()
    private Country country;

    @ManyToOne()
    private Customer customer;


}
