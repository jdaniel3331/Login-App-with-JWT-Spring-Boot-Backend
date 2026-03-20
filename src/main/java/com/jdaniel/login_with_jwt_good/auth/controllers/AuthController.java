package com.jdaniel.login_with_jwt_good.auth.controllers;

import com.jdaniel.login_with_jwt_good.auth.models.dto.LoginRequest;
import com.jdaniel.login_with_jwt_good.auth.models.dto.LoginResponse;
import com.jdaniel.login_with_jwt_good.auth.models.dto.RefreshRequest;
import com.jdaniel.login_with_jwt_good.auth.service.AuthService;
import com.jdaniel.login_with_jwt_good.common.response.ApiResponse;
import com.jdaniel.login_with_jwt_good.user.dto.CreateUserDto;
import com.jdaniel.login_with_jwt_good.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody CreateUserDto newUser) {
        String message = userService.registerUser(newUser);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.CREATED.name(),message, HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest  loginRequest, HttpServletRequest httpRequest) {
        String[] tokens = authService.login(loginRequest, httpRequest);
        LoginResponse loginResponse = new LoginResponse(tokens[0], tokens[1]);
        ApiResponse<LoginResponse> response = new ApiResponse<>(HttpStatus.OK.name(), "Success login", HttpStatus.OK.value(), loginResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponse>> refresh(@RequestBody RefreshRequest refreshRequest, HttpServletRequest httpRequest) {
        String[] tokens = authService.refresh(refreshRequest, httpRequest);
        LoginResponse loginResponse = new LoginResponse(tokens[0], tokens[1]);
        ApiResponse<LoginResponse> response = new ApiResponse<>(HttpStatus.OK.name(), "Success refresh", HttpStatus.OK.value(), loginResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String testTokenAuth(){
        return "Hola desde el enpoint auth/test";
    }
}
