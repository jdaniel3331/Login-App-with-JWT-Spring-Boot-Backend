package com.jdaniel.login_with_jwt_good.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdaniel.login_with_jwt_good.user.models.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	
	Optional<User> findByEmail(String email);

}
