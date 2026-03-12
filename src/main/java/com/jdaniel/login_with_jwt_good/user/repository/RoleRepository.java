package com.jdaniel.login_with_jwt_good.user.repository;

import com.jdaniel.login_with_jwt_good.user.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Short> {

    Optional<Role> findRoleByName(String name);
}
