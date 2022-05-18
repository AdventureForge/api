package com.adventureforge.gameservice.controllers.wrappers;


import com.adventureforge.gameservice.dto.View;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

//@Schema(name = "Response")
@JsonView(View.External.GET.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class ResponseWrapper<T> {

    private T data;
    private HttpStatus status;
    private int statusCode;
    private int length;
    private String message;
    private String debugMessage;
    private List<ISubErrorWrapper> subErrors;
    private boolean paginated = false;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    PageInfo pageInfo;

    @JsonView(View.External.GET.class)
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class PageInfo {
        private boolean firstElement;
        private boolean lastElement;
        private long totalElements;
        private int totalPages;
        private int pageNumber;
        private int pageSize;
    }

    private ResponseWrapper() {
        timestamp = LocalDateTime.now();
    }

    public ResponseWrapper(HttpStatus status) {
        this();
        this.status = status;
        this.statusCode = status.value();
    }

    public ResponseWrapper(HttpStatus status, Throwable ex) {
        this(status);
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ResponseWrapper(HttpStatus status, String message, Throwable ex) {
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

    public static <T> ResponseWrapper<T> wrap(T t) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        if (t instanceof Collection<?>) {
            responseWrapper.setLength(((Collection<?>) t).size());
            responseWrapper.setData(t);
        } else if (t instanceof Page<?>) {
            responseWrapper.setData((T) ((Page<T>) t).getContent());
            responseWrapper.setLength(((Page<?>) t).getContent().size());
            responseWrapper.setPaginated(true);
            responseWrapper.setPageInfo(PageInfo.builder()
                    .firstElement(((Page<?>) t).isFirst())
                    .lastElement(((Page<?>) t).isLast())
                    .totalElements(((Page<?>) t).getTotalElements())
                    .totalPages(((Page<?>) t).getTotalPages())
                    .pageNumber(((Page<?>) t).getPageable().getPageNumber())
                    .pageSize(((Page<?>) t).getSize())
                    .build()
            );
        } else {
            responseWrapper.setData(t);
            responseWrapper.setLength(1);
        }
        return responseWrapper;
    }

    public static <T> ResponseWrapper<T> wrapPageToList(Page<?> page) {
        return ResponseWrapper.wrap((T) page);
    }
}
