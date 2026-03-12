package com.jdaniel.login_with_jwt_good.common.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ApiResponse<T> (
        String timestamp,
        String status,
        String message,
        int code,
        T data
){
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public ApiResponse(String status, String message, int code){
        this(LocalDateTime.now().format(FORMATTER), status, message, code, null);
    }

    public ApiResponse(String status, String message, int code, T data){
        this(LocalDateTime.now().format(FORMATTER), status, message, code, data);
    }
}
