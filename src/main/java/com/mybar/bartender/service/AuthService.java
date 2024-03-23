package com.mybar.bartender.service;

import com.art.playersapi.exception.ApiError;
import com.mybar.bartender.dto.*;
import com.mybar.bartender.model.User;
import com.mybar.bartender.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, refreshToken));
    }
    //TODO: -Not Check
    public ResponseEntity<?> refresh(RefreshJwtRequest refreshToken) {
        var refreshTokenValue = refreshToken.getRefreshToken();
        if (jwtTokenUtils.validateRefreshToken(refreshTokenValue)) {
            final Claims claims = jwtTokenUtils.getRefreshClaims(refreshTokenValue);
            final String login = claims.getSubject();
            UserDetails user = userService.loadUserByUsername(login);
            final String accessToken = jwtTokenUtils.generateToken(user);
            final String newRefreshToken = jwtTokenUtils.generateRefreshToken(user);
            return ResponseEntity.ok(new JwtResponse(accessToken, newRefreshToken));
        }
        return new ResponseEntity<>(new ApiError(HttpStatus.UNAUTHORIZED.toString(), "Невалидный JWT токен"), HttpStatus.UNAUTHORIZED);
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

    public Long getUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}