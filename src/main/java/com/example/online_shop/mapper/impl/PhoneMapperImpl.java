package com.example.online_shop.mapper.impl;

import com.example.online_shop.dto.phone.PhoneResponse;
import com.example.online_shop.entities.Phone;
import com.example.online_shop.mapper.PhoneMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PhoneMapperImpl implements PhoneMapper {
    @Override
    public List<PhoneResponse> toDtoS(List<Phone> all) {
        List<PhoneResponse> phoneResponses = new ArrayList<>();
        for(Phone phone: all) {
            phoneResponses.add(toDto(phone));
        }
        return phoneResponses;
    }

    public PhoneResponse toDto(Phone phone) {
        PhoneResponse phoneResponse = new PhoneResponse();
        phoneResponse.setName(phone.getName());
        phoneResponse.setId(phone.getId());
        phoneResponse.setPrice(phone.getPrice());
        phoneResponse.setSerialNumber(phone.getSerialNumber());
        phoneResponse.setDate(phone.getDate());
        phoneResponse.setRam(phone.getRam());
        phoneResponse.setCountry(phone.getCountry().getName());
        phoneResponse.setIsExist(phone.getIsExist());

        return phoneResponse;
    }
}
