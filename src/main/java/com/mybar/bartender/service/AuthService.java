package com.mybar.bartender.service;

import com.art.playersapi.exception.ApiError;
import com.mybar.bartender.dto.JwtRequest;
import com.mybar.bartender.dto.JwtResponse;


import com.mybar.bartender.dto.RegistrationUserDto;
import com.mybar.bartender.dto.UserDto;
import com.mybar.bartender.model.User;
import com.mybar.bartender.service.UserService;
import com.mybar.bartender.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.UNAUTHORIZED.toString(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {

        if (userService.findByUsername(registrationUserDto.getUsername()) != null) {
            return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST.toString(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByEmail(registrationUserDto.getEmail()) != null) {
            return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST.toString(), "Пользователь с указанным email уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getName(), user.getEmail()));
    }
}