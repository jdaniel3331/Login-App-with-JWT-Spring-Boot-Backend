package com.jdaniel.login_with_jwt_good.auth.service;

import com.jdaniel.login_with_jwt_good.auth.models.RefreshToken;
import com.jdaniel.login_with_jwt_good.auth.repository.RefreshTokenRepository;
import com.jdaniel.login_with_jwt_good.user.models.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    @Value("${refresh.token.expirationTimeDays}")
    private int expirationTime;

    public String generateRefreshToken(User user, HttpServletRequest request, String familyId){
        String rawToken = UUID.randomUUID().toString();
        String tokenHash = hashToken(rawToken);

        String  newFamilyId = familyId != null ? familyId : UUID.randomUUID().toString();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(tokenHash);
        refreshToken.setExpiresAt(OffsetDateTime.now().plusDays(expirationTime));
        refreshToken.setRevoked(false);

        refreshToken.setTokenFamily(UUID.fromString(newFamilyId));
        refreshToken.setDeviceId(request.getHeader("X-Device-Id")); // El device id lo genera el cliente
        refreshToken.setUserAgent(request.getHeader("User-Agent"));
        refreshToken.setIpAddress(hashToken(getClientIp(request)));

        refreshTokenRepository.save(refreshToken);

        return rawToken;

    }

    public RefreshToken validateRefreshToken(String token) {
        String tokenHash = hashToken(token);

        RefreshToken refreshToken = refreshTokenRepository.findByToken(tokenHash)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (refreshToken.isRevoked()) {
            revokeTokenFamily(refreshToken.getTokenFamily());
            throw new RuntimeException("Refresh token has been used. Family revoked");
        }

        if (refreshToken.getExpiresAt().isBefore(OffsetDateTime.now())) throw new RuntimeException("Refresh token has expired");

        return refreshToken;
    }

    public void revokeAllUserTokens(User u) {
        List<RefreshToken> tokens = refreshTokenRepository.findAllByUserAndIsRevokedFalse(u);
        tokens.forEach(token -> token.setRevoked(true));
        refreshTokenRepository.saveAll(tokens);
    }

    public void revokeToken(RefreshToken refreshToken) {
        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }

    private String hashToken(String rawToken) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawToken.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        return forwarded != null ? forwarded.split(",")[0].trim() : request.getRemoteAddr();
    }

    private void revokeTokenFamily(UUID familyId) {
        List<RefreshToken> tokens = refreshTokenRepository.findAllByTokenFamily(familyId);
        tokens.forEach(token -> token.setRevoked(true));
        refreshTokenRepository.saveAll(tokens);
    }
}
