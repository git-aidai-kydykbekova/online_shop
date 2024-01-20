package com.example.online_shop.service;

import com.example.online_shop.dto.country.CountryResponse;

import java.util.List;

public interface CountryService {
    void addCountry(String name, String token);

    List<CountryResponse> getAll();
}
