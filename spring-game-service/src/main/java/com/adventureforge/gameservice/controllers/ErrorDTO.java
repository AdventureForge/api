package com.adventureforge.gameservice.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO<T extends Serializable> implements java.io.Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T body;

    private String message;

    public ErrorDTO(T body, String message) {
        this.body = body;
        this.message = message;
    }
}