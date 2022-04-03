package com.adventureforge.gameservice.controllers.handlers;

import com.adventureforge.gameservice.controllers.wrappers.ErrorResponseWrapper;
import com.adventureforge.gameservice.controllers.wrappers.ISubErrorWrapper;
import com.adventureforge.gameservice.controllers.wrappers.SuccessResponseWrapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

import java.util.Collection;

@Slf4j
@ControllerAdvice
public class CustomResponseAdvice extends JsonViewResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getContainingClass().getSimpleName().contains("Controller");
    }

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

        Object body = bodyContainer.getValue();
        boolean tobeModified = !(body instanceof SuccessResponseWrapper<?> || body instanceof ErrorResponseWrapper || body instanceof ISubErrorWrapper);

        if (tobeModified) {
            SuccessResponseWrapper<Object> responseWrapper = new SuccessResponseWrapper<>();
            responseWrapper.setStatusCode(((ServletServerHttpResponse) response).getServletResponse().getStatus());
            if (body instanceof Collection<?>) {
                responseWrapper.setLength(((Collection<?>) body).size());
                responseWrapper.setData(body);
            } else if (body instanceof Page<?>) {
                responseWrapper.setData(((Page<?>) body).getContent());
                responseWrapper.setLength(((Page<?>) body).getContent().size());
                responseWrapper.setPaginated(true);
                responseWrapper.setPageInfo(SuccessResponseWrapper.PageInfo.builder()
                        .firstElement(((Page<?>) body).isFirst())
                        .lastElement(((Page<?>) body).isLast())
                        .totalElements(((Page<?>) body).getTotalElements())
                        .totalPages(((Page<?>) body).getTotalPages())
                        .pageNumber(((Page<?>) body).getPageable().getPageNumber())
                        .pageSize(((Page<?>) body).getSize())
                        .build()
                );
            } else {
                responseWrapper.setData(body);
                responseWrapper.setLength(1);
            }
            bodyContainer.setValue(responseWrapper);
        }
    }
}