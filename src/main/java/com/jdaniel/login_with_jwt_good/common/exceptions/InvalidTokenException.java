package com.jdaniel.login_with_jwt_good.common.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
public class InvalidTokenException extends RuntimeException{
    private String status;
    private String message;
    private int code;

    public InvalidTokenException(String message) {
        super(message);
        this.status = HttpStatus.UNAUTHORIZED.name();
        this.message = message;
        this.code = HttpStatus.UNAUTHORIZED.value();
    }
}
