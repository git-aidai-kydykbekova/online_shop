package com.example.online_shop.mapper.impl;

import com.example.online_shop.dto.country.CountryResponse;
import com.example.online_shop.entities.Country;
import com.example.online_shop.mapper.CountryMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CountryMapperImpl implements CountryMapper {
    @Override
    public List<CountryResponse> toDtoS(List<Country> all) {
        List<CountryResponse> countryResponses = new ArrayList<>();
        for(Country country: all) {
            countryResponses.add(toDto(country));
        }
        return countryResponses;
    }

    private CountryResponse toDto(Country country) {
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setId(country.getId());
        countryResponse.setName(country.getName());
        return countryResponse;
    }
}
