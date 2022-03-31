package com.adventureforge.gameservice.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse<T extends Serializable> {

    private ErrorDTO<T> error;

    public ErrorResponse(T object, String message) {
        error = new ErrorDTO<>(object, message);
    }
}