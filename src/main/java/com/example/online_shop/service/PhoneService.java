package com.example.online_shop.service;

import com.example.online_shop.dto.phone.PhoneAddRequest;
import com.example.online_shop.dto.phone.PhoneResponse;

import java.util.List;

public interface PhoneService {

    void addPhone(PhoneAddRequest request, String token);

    List<PhoneResponse> getAll(String token);

    void buyPhone(Long phoneId, String token);

    List<PhoneResponse> getMyPhones(String token);

    void deletePhone(Long phoneId, String token);
}
