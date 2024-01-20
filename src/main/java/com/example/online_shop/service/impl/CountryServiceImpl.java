package com.example.online_shop.service.impl;

import com.example.online_shop.dto.country.CountryResponse;
import com.example.online_shop.entities.Country;
import com.example.online_shop.exception.NotFoundException;
import com.example.online_shop.mapper.CountryMapper;
import com.example.online_shop.repositories.CountryRepository;
import com.example.online_shop.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor

public class CountryServiceImpl implements CountryService {

    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;

    @Override
    public void addCountry(String name, String token){
        if(countryRepository.findByName(name).isPresent()) {
            throw new NotFoundException("country with name: "+name+" is already exist!", HttpStatus.BAD_REQUEST);

        }
        Country country = new Country();
        country.setName(name);
        countryRepository.save(country);

    }

    @Override
    public List<CountryResponse> getAll(){
        return countryMapper.toDtoS(countryRepository.findAll());
    }
}
