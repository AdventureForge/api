package com.adventureforge.gameservice.controllers;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@ControllerAdvice
public class CustomResponseAdvise implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (methodParameter.getContainingClass().isAnnotationPresent(RestController.class)
                && !Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(IgnoreResponseBinding.class)
                && (!(o instanceof ErrorResponse)) && (!(o instanceof SuccessResponse))) {
            return new SuccessResponse<Object>(o);
        }
        return o;
    }
}
