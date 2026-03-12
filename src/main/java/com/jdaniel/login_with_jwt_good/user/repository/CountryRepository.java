package com.jdaniel.login_with_jwt_good.user.repository;

import com.jdaniel.login_with_jwt_good.user.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Short> {

    Optional<Country> findByCountryId(Short countryId);
}
