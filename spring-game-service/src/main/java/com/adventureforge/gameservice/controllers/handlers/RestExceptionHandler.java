package com.adventureforge.gameservice.controllers.handlers;

import com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper;
import com.adventureforge.gameservice.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        String error = ex.getParameterName() + " parameter is missing";
        return this.buildResponseEntity(new ResponseWrapper<>(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {

        StringBuilder builder = new StringBuilder()
                .append(ex.getContentType())
                .append(" media type is not supported. Supported media types are ")
                .append(
                        ex.getSupportedMediaTypes()
                                .stream()
                                .map(MediaType::toString)
                                .collect(Collectors.joining(", "))
                );

        return this.buildResponseEntity(new ResponseWrapper<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.toString(), ex));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(HttpStatus.BAD_REQUEST);
        responseWrapper.setMessage("Validation error");
        responseWrapper.addValidationErrors(ex.getBindingResult().getFieldErrors());
        responseWrapper.addValidationError(ex.getBindingResult().getGlobalErrors());
        return this.buildResponseEntity(responseWrapper);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(HttpStatus.BAD_REQUEST, "Illegal argument", ex);
        return this.buildResponseEntity(responseWrapper);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(HttpStatus.BAD_REQUEST);
        responseWrapper.setMessage("Validation error");
        responseWrapper.addValidationErrors(ex.getConstraintViolations());
        return this.buildResponseEntity(responseWrapper);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(HttpStatus.NOT_FOUND);
        responseWrapper.setMessage(ex.getMessage());
        return this.buildResponseEntity(responseWrapper);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String error = "Malformed JSON request";
        return this.buildResponseEntity(new ResponseWrapper<>(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex) {
        String error = "Error writing JSON output";
        return this.buildResponseEntity(new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {

        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(HttpStatus.BAD_REQUEST);
        responseWrapper.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
        responseWrapper.setDebugMessage(ex.getMessage());
        return this.buildResponseEntity(responseWrapper);
    }

    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
        return this.buildResponseEntity(new ResponseWrapper<>(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return (ex.getCause() instanceof ConstraintViolationException)
                ?
                this.buildResponseEntity(new ResponseWrapper<>(HttpStatus.CONFLICT, "Database error", ex.getCause()))
                :
                this.buildResponseEntity(new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(HttpStatus.BAD_REQUEST);
        responseWrapper.setMessage(
                String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                        ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName())
        );
        responseWrapper.setDebugMessage(ex.getMessage());
        return this.buildResponseEntity(responseWrapper);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        ResponseWrapper<Object> responseWrapper = new ResponseWrapper<>(HttpStatus.FORBIDDEN);
        responseWrapper.setMessage("You are not allowed to access this resource");
        responseWrapper.setDebugMessage(ex.getMessage());
        return this.buildResponseEntity(responseWrapper);
    }

    private ResponseEntity<Object> buildResponseEntity(ResponseWrapper<Object> responseWrapper) {
        return new ResponseEntity<>(responseWrapper, responseWrapper.getStatus());
    }
}