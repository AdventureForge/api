package com.adventureforge.gameservice.controllers.responsewrappers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("data")
public class ErrorResponseWrapper {

    private HttpStatus status;
    private int statusCode;
    private String message;
    private String debugMessage;
    private List<ISubErrorWrapper> subErrors;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private ErrorResponseWrapper() {
        timestamp = LocalDateTime.now();
    }

    public ErrorResponseWrapper(HttpStatus status) {
        this();
        this.status = status;
        this.statusCode = status.value();
    }

    public ErrorResponseWrapper(HttpStatus status, Throwable ex) {
        this(status);
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ErrorResponseWrapper(HttpStatus status, String message, Throwable ex) {
        this(status, ex);
        this.message = message;
    }

    private void addSubError(ISubErrorWrapper subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ValidationErrorWrapper(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        addSubError(new ValidationErrorWrapper(object, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ConstraintViolation<?> constraintViolation) {
        this.addValidationError(
                constraintViolation.getRootBeanClass().getSimpleName(),
                ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().asString(),
                constraintViolation.getInvalidValue(),
                constraintViolation.getMessage());
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }
}