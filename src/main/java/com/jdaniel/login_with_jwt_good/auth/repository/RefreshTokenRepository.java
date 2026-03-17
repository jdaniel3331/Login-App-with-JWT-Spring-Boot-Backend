package com.jdaniel.login_with_jwt_good.auth.repository;

import com.jdaniel.login_with_jwt_good.auth.models.RefreshToken;
import com.jdaniel.login_with_jwt_good.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByTokenFamily(UUID familyId);
    List<RefreshToken> findAllByUserAndIsRevokedFalse(User u);
}
