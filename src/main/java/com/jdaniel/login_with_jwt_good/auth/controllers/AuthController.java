package com.jdaniel.login_with_jwt_good.auth.controllers;

import com.jdaniel.login_with_jwt_good.user.dto.CreateUserDto;
import com.jdaniel.login_with_jwt_good.user.service.UserService;
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
    public String register(@RequestBody CreateUserDto newUser) {
        return userService.registerUser(newUser);
    }
}
