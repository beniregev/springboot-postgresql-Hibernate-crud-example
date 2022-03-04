package com.beniregev.springboot_postgresql_hibernate_crud_example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

}
