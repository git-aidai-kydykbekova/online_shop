package com.example.online_shop.controller;

import com.example.online_shop.dto.country.CountryResponse;
import com.example.online_shop.repositories.CountryRepository;
import com.example.online_shop.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;


    @PostMapping("/add")
    public void addCountry(@RequestParam String name, @RequestHeader("Authorization") String token){
        countryService.addCountry(name, token);
    }

    @GetMapping("/countries")
    public List<CountryResponse> countryResponses(){
        return countryService.getAll();
    }
}
