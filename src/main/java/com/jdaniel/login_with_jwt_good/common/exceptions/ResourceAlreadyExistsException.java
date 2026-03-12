package com.jdaniel.login_with_jwt_good.common.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
public class ResourceAlreadyExistsException extends RuntimeException{
    private String status;
    private String message;
    private int code;

    public ResourceAlreadyExistsException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT.name();
        this.message = message;
        this.code = HttpStatus.CONFLICT.value();
    }
}
