package com.jdaniel.login_with_jwt_good.user.dto;

public record CreateUserDto(
		String name,
		String lastName,
		String email,
		String password,
		String telephoneNum,
		Short countryId
){}
