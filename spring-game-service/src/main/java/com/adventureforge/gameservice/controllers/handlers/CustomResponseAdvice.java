package com.adventureforge.gameservice.controllers.handlers;

import com.adventureforge.gameservice.controllers.responsewrappers.SuccessResponseWrapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collection;

@Slf4j
@ControllerAdvice
public class CustomResponseAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        log.info(converterType.getSimpleName());
        return returnType.getContainingClass().getSimpleName().contains("Controller");
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        SuccessResponseWrapper<Object> responseWrapper = new SuccessResponseWrapper<>();
        responseWrapper.setStatusCode(((ServletServerHttpResponse) response).getServletResponse().getStatus());
        responseWrapper.setData(body);
        if (body instanceof Collection<?>) {
            responseWrapper.setLength(((Collection<?>) body).size());
        } else {
            responseWrapper.setLength(1);
        }
        return responseWrapper;
    }
}