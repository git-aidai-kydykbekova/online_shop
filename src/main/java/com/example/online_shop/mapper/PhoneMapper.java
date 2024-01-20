package com.example.online_shop.mapper;

import com.example.online_shop.dto.country.CountryResponse;
import com.example.online_shop.dto.phone.PhoneResponse;
import com.example.online_shop.entities.Country;
import com.example.online_shop.entities.Phone;

import java.util.List;

public interface PhoneMapper {
    List<PhoneResponse> toDtoS(List<Phone> all);

    PhoneResponse toDto(Phone phone);
}
