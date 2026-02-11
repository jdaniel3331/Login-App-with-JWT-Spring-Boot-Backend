package com.jdaniel.login_with_jwt_good.auth.models;

import com.jdaniel.login_with_jwt_good.user.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens", schema = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue
    @Column(name = "refresh_token_id", nullable = false, updatable = false)
    private UUID id;
    @Column(name = "refresh_token_hash",nullable = false)
    private String token;
    @CreationTimestamp
    @Column(name = "created_at",  nullable = false, updatable = false)
    private OffsetDateTime createdAt;
    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;
    @Column(name = "is_revoked", nullable = false)
    private boolean isRevoked;
    @Column(name = "device_id")
    private UUID deviceId;
    @Column(name = "token_family_id")
    private UUID tokenFamily;
    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;
    @Column(name = "ip_address_hash", columnDefinition = "TEXT")
    private String ipAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
