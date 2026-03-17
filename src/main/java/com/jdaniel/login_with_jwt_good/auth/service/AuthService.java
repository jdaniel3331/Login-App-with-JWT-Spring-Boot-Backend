package com.jdaniel.login_with_jwt_good.auth.service;

import com.jdaniel.login_with_jwt_good.auth.models.dto.LoginRequest;
import com.jdaniel.login_with_jwt_good.common.utils.JwtService;
import com.jdaniel.login_with_jwt_good.user.models.User;
import com.jdaniel.login_with_jwt_good.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl  userDetailsService;
    private final UserRepository userRepository;

    public String[] login(LoginRequest request, HttpServletRequest httpRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        String jwtToken = jwtService.generateToken(userDetails);

        User u = userRepository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("User not found"));
        String refreshToken = refreshTokenService.generateRefreshToken(u, httpRequest, null);

        return new String[]{jwtToken, refreshToken};
    }
}
