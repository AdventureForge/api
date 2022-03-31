package com.adventureforge.gameservice.controllers;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SuccessResponse<T extends Serializable> {

    private SuccessDTO<T> response;

    public SuccessResponse(T object) {
        this.response = new SuccessDTO<>(object);
    }

    public SuccessResponse(T object, String message) {
        this.response = new SuccessDTO<>(object, message);
    }

    public SuccessResponse(T object, Integer length, String message) {
        this.response = new SuccessDTO<>(object, length, message);
    }

    public SuccessResponse(T object, Integer length) {
        this.response = new SuccessDTO<>(object, length);
    }
}