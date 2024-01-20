package com.example.online_shop.service.impl;

import com.example.online_shop.dto.phone.PhoneAddRequest;
import com.example.online_shop.dto.phone.PhoneResponse;
import com.example.online_shop.entities.Country;
import com.example.online_shop.entities.Phone;
import com.example.online_shop.entities.User;
import com.example.online_shop.enums.Role;
import com.example.online_shop.exception.BadCredentialsException;
import com.example.online_shop.exception.NotFoundException;
import com.example.online_shop.mapper.PhoneMapper;
import com.example.online_shop.repositories.CountryRepository;
import com.example.online_shop.repositories.PhoneRepository;
import com.example.online_shop.repositories.UserRepository;
import com.example.online_shop.service.AuthService;
import com.example.online_shop.service.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private final AuthService authService;
    private final PhoneRepository phoneRepository;
    private final CountryRepository countryRepository;
    private final PhoneMapper phoneMapper;
    private final UserRepository userRepository;

    @Override
    public void addPhone(PhoneAddRequest phoneAddRequest, String token) {

        if(phoneRepository.findBySerialNumber(phoneAddRequest.getSerialNumber()).isPresent()) {
            throw new NotFoundException("phone with this serial Number is already exist!: "+phoneAddRequest.getSerialNumber(),
                    HttpStatus.BAD_REQUEST);
        }
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function only for admin!");

        Phone phone = new Phone();
        phone.setName(phoneAddRequest.getName());
        phone.setDate(LocalDateTime.now().toString());
        phone.setPrice(phoneAddRequest.getPrice());
        phone.setSerialNumber(phoneAddRequest.getSerialNumber());
        phone.setRam(phoneAddRequest.getRam());
        phone.setIsExist(true);
        Optional<Country> country = countryRepository.findByName(phoneAddRequest.getCountry());
        if(country.isEmpty()) {
            throw new NotFoundException("no country with name: "+ phoneAddRequest.getCountry(), HttpStatus.BAD_REQUEST);
        }
        phone.setCountry(country.get());
        phoneRepository.save(phone);
    }

    @Override
    public List<PhoneResponse> getAll(String token) {
        User user = authService.getUsernameFromToken(token);

        if(!user.getRole().equals(Role.ADMIN)) {
            System.out.println("user");
            List<PhoneResponse> phoneResponses = phoneMapper.toDtoS(phoneRepository.findAllByIsExist(true));
            return phoneResponses;
        }
        return phoneMapper.toDtoS(phoneRepository.findAll());
    }

    @Override
    public void buyPhone(Long phoneId, String token) {
        User user = authService.getUsernameFromToken(token);
        Optional<Phone> phone = phoneRepository.findById(phoneId);
        if(phone.isEmpty()){
            throw new NotFoundException("this phone sold", HttpStatus.BAD_REQUEST);
        }
        phone.get().setIsExist(false);
        List<Phone> phones = new ArrayList<>();
        if(!user.getCustomer().getPhones().isEmpty()) {
            phones = user.getCustomer().getPhones();
        }
        phones.add(phone.get());
        user.getCustomer().setPhones(phones);
        userRepository.save(user);
    }

    @Override
    public List<PhoneResponse> getMyPhones(String token) {
        User user = authService.getUsernameFromToken(token);

        if(!user.getRole().equals(Role.ADMIN)) {
            List<PhoneResponse> phoneResponses = phoneMapper.toDtoS(user.getCustomer().getPhones());
            return phoneResponses;
        }
        return null;
    }

    @Override
    public void deletePhone(Long phoneId, String token) {
        User user = authService.getUsernameFromToken(token);

        if(user.getRole().equals(Role.ADMIN)) {
            phoneRepository.deleteById(phoneId);
        }
        else
            throw new NotFoundException("This function is available only for ADMIN", HttpStatus.BAD_REQUEST);
    }
}
