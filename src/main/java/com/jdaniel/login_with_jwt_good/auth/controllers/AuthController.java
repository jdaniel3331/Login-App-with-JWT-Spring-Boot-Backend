package com.jdaniel.login_with_jwt_good.auth.controllers;

import com.jdaniel.login_with_jwt_good.common.response.ApiResponse;
import com.jdaniel.login_with_jwt_good.user.dto.CreateUserDto;
import com.jdaniel.login_with_jwt_good.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody CreateUserDto newUser) {
        String message = userService.registerUser(newUser);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.CREATED.name(),message, HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
