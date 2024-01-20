package com.example.online_shop.service.impl;

import com.example.online_shop.config.JwtService;
import com.example.online_shop.dto.user.UserRegisterRequest;
import com.example.online_shop.dto.authen.AuthLoginRequest;
import com.example.online_shop.dto.authen.AuthLoginResponse;
import com.example.online_shop.entities.Customer;
import com.example.online_shop.entities.User;
import com.example.online_shop.enums.Role;
import com.example.online_shop.exception.BadCredentialsException;
import com.example.online_shop.repositories.UserRepository;
import com.example.online_shop.service.AuthService;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        if(userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent()) {
            throw new BadCredentialsException("user with email: "+userRegisterRequest.getEmail()+" is already exist!");
        }
        User user = new User();
        user.setRole(Role.CUSTOMER);
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        Customer customer = new Customer();
        customer.setAge(userRegisterRequest.getAge());
        customer.setName(userRegisterRequest.getName());
        customer.setUser(user);
        user.setCustomer(customer);

        userRepository.save(user);

    }

    @Override
    public AuthLoginResponse login(AuthLoginRequest authLoginRequest) {
        Optional<User> user = userRepository.findByEmail(authLoginRequest.getEmail());
        if(user.isEmpty()) {
            throw new BadCredentialsException("user is not found");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginRequest.getEmail(),authLoginRequest.getPassword()));

        }catch (org.springframework.security.authentication.BadCredentialsException e){
            throw new BadCredentialsException("user not found");
        }
        return convertToResponse(user);
    }
    private AuthLoginResponse convertToResponse(Optional<User> user) {
        AuthLoginResponse authLoginResponse = new AuthLoginResponse();
        authLoginResponse.setEmail(user.get().getEmail());
        authLoginResponse.setId(user.get().getId());
        if (user.get().getRole().equals(Role.CUSTOMER))
            authLoginResponse.setName(user.get().getCustomer().getName());
        Map<String, Object> extraClaims = new HashMap<>();

        String token = jwtService.generateToken(extraClaims, user.get());
        authLoginResponse.setToken(token);

        return authLoginResponse;
    }
    @Override
    public User getUsernameFromToken(String token){

        String[] chunks = token.substring(7).split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        JSONParser jsonParser = new JSONParser();
        JSONObject object = null;
        try {
            object = (JSONObject) jsonParser.parse(decoder.decode(chunks[1]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return userRepository.findByEmail(String.valueOf(object.get("sub"))).orElseThrow(() -> new RuntimeException("user can be null"));
    }
}
