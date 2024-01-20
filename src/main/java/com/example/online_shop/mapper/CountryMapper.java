package com.example.online_shop.mapper;

import com.example.online_shop.dto.country.CountryResponse;
import com.example.online_shop.entities.Country;

import java.util.List;

public interface CountryMapper {
    List<CountryResponse> toDtoS(List<Country> all);
}
