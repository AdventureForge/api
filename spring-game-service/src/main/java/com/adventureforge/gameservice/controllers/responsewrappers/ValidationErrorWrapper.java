package com.adventureforge.gameservice.controllers.responsewrappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ValidationErrorWrapper implements ISubErrorWrapper {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ValidationErrorWrapper(String object, String message) {
        this.object = object;
        this.message = message;
    }
}