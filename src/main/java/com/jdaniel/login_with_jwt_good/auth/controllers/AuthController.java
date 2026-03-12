package com.jdaniel.login_with_jwt_good.auth.controllers;

import com.jdaniel.login_with_jwt_good.auth.models.dto.LoginRequest;
import com.jdaniel.login_with_jwt_good.auth.models.dto.LoginResponse;
import com.jdaniel.login_with_jwt_good.auth.service.UserDetailsServiceImpl;
import com.jdaniel.login_with_jwt_good.common.response.ApiResponse;
import com.jdaniel.login_with_jwt_good.common.utils.JwtService;
import com.jdaniel.login_with_jwt_good.user.dto.CreateUserDto;
import com.jdaniel.login_with_jwt_good.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl  userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody CreateUserDto newUser) {
        String message = userService.registerUser(newUser);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.CREATED.name(),message, HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest  loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.email());
        String token = jwtService.generateToken(userDetails);
        LoginResponse loginResponse = new LoginResponse(token);
        ApiResponse<LoginResponse> response = new ApiResponse<>(HttpStatus.OK.name(), "Success login", HttpStatus.OK.value(), loginResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String testTokenAuth(){
        return "Hola desde el enpoint auth/test";
    }
}
