package com.example.employee.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NoSuchEmployeeException extends HttpStatusCodeException {
    public NoSuchEmployeeException() {
        super(HttpStatus.NOT_FOUND);
    }
}
