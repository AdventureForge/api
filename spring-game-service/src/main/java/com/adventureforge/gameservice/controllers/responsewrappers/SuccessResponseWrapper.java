package com.adventureforge.gameservice.controllers.responsewrappers;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuccessResponseWrapper<T> {

    private T data;
    private String message;
    private int length;
    private int statusCode;
}
