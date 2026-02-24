package com.jdaniel.login_with_jwt_good.user.dto;

public record CreateUserDto(
		String firstName,
		String lastName,
		String email,
		String password,
		String telephoneNum,
		Short countryId
){}
