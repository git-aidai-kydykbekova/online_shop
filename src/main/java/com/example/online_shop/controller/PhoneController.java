package com.example.online_shop.controller;

import com.example.online_shop.dto.phone.PhoneAddRequest;
import com.example.online_shop.dto.phone.PhoneResponse;
import com.example.online_shop.service.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phone")
@AllArgsConstructor

public class PhoneController {
    private PhoneService phoneService;

    @PostMapping("/add")
    public void addPhone(@RequestBody PhoneAddRequest phoneAddRequest, @RequestHeader("Authorization") String token) {
        phoneService.addPhone(phoneAddRequest, token);
    }

    @GetMapping("/list")
    public List<PhoneResponse> phoneResponses(@RequestHeader("Authorization") String token) {
        return phoneService.getAll(token);
    }

    @PostMapping("/buy/{phoneId}")
    public void buyPhone(@PathVariable Long phoneId, @RequestHeader("Authorization") String token){
        phoneService.buyPhone(phoneId,token);
    }

    @GetMapping("/my/basket")
    public List<PhoneResponse> myPhones(@RequestHeader("Authorization") String token) {
        return phoneService.getMyPhones(token);
    }

    @DeleteMapping("/delete/{phoneId}")
    public void delete(@PathVariable Long phoneId,@RequestHeader("Authorization") String token) {
        phoneService.deletePhone(phoneId,token);
    }
}
