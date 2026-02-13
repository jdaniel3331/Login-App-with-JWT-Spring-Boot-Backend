package com.jdaniel.login_with_jwt_good.user.service;

import java.util.Optional;

import com.jdaniel.login_with_jwt_good.user.models.Country;
import com.jdaniel.login_with_jwt_good.user.models.Role;
import com.jdaniel.login_with_jwt_good.user.repository.CountryRepository;
import com.jdaniel.login_with_jwt_good.user.repository.RoleRepository;
import org.springframework.stereotype.Service;

import com.jdaniel.login_with_jwt_good.user.dto.CreateUserDto;
import com.jdaniel.login_with_jwt_good.user.models.User;
import com.jdaniel.login_with_jwt_good.user.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final CountryRepository countryRepository;
	private final RoleRepository roleRepository;
	
	public UserService(UserRepository userRepository, CountryRepository countryRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.countryRepository = countryRepository;
		this.roleRepository = roleRepository;
	}

	public String registerUser(CreateUserDto newUser) {
		Optional<User> userFromDb = userRepository.findByEmail(newUser.email());
		
		if(userFromDb.isPresent()) return "User already exists!";
		
		User userToBeRegistered = new User();
		userToBeRegistered.setFirstName(newUser.firstName());
		userToBeRegistered.setMiddleName(newUser.middleName());
		userToBeRegistered.setLastName(newUser.lastName());
		userToBeRegistered.setSecondLastName(newUser.secondLastName());
		userToBeRegistered.setEmail(newUser.email());
		userToBeRegistered.setPassword(newUser.password());
		userToBeRegistered.setTelephone(newUser.telephoneNum());

		Optional<Country> countryFromDb = countryRepository.findByCountryId(newUser.countryId());
		if(countryFromDb.isEmpty()) return "Country not found!";

		userToBeRegistered.setCountry(countryFromDb.get());

		// Por el momento todos lo usuarios registrados serán USER
		Optional<Role> roleFromDb = roleRepository.findRoleByName("USER");
		if(roleFromDb.isEmpty()) return "Role not found!";

		userToBeRegistered.setRole(roleFromDb.get());

		userRepository.save(userToBeRegistered);
		
		return "User registered successfully!";
	}

}
