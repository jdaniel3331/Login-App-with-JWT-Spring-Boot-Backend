package com.jdaniel.login_with_jwt_good.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue // Hibernate detecta que la columna tiene un valor por defecto en la bd
    @Column( name = "user_id", nullable = false, updatable = false)
    private UUID userId;
    @Column( name = "name", nullable = false, length = 100)
    private String name;
    @Column( name = "last_name", nullable = false, length = 100)
    private String lastName;
    @Column( name = "email", nullable = false, unique = true)
    private String email;
    @Column( name = "password_hash", nullable = false)
    private String password;
    @Column( name = "telephone_num", nullable = false, length = 15)
    private String telephone;
    @Column( name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private boolean isActive = true;
    @Column( name = "is_verified", nullable = false, columnDefinition = "boolean default false")
    private boolean isVerified;
    @CreationTimestamp
    @Column( name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt; // Fecha y hora con zoma horaria
    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
    @Column( name = "verified_at")
    private OffsetDateTime verifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
