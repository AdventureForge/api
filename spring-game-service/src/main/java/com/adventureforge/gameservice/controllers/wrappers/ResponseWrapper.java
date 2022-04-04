package com.adventureforge.gameservice.controllers.wrappers;


import com.adventureforge.gameservice.dto.View;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.Collection;

//@Schema(name = "Response")
@JsonView(View.External.GET.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseWrapper<T> {

    private T data;
    private String message;
    private int length;
    private int statusCode;
    private boolean paginated = false;

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

    public static <T> ResponseWrapper<T> wrap(T t) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        if (t instanceof Collection<?>) {
            responseWrapper.setLength(((Collection<?>) t).size());
            responseWrapper.setData(t);
        } else if (t instanceof Page<?>) {
            responseWrapper.setData((T) ((Page<T>) t).getContent());
            responseWrapper.setLength(((Page<?>) t).getContent().size());
            responseWrapper.setPaginated(true);
            responseWrapper.setPageInfo(ResponseWrapper.PageInfo.builder()
                    .firstElement(((Page<?>) t).isFirst())
                    .lastElement(((Page<?>) t).isLast())
                    .totalElements(((Page<?>) t).getTotalElements())
                    .totalPages(((Page<?>) t).getTotalPages())
                    .pageNumber(((Page<?>) t).getPageable().getPageNumber())
                    .pageSize(((Page<?>) t).getSize())
                    .pageSize(((Page<?>) t).getSize())
                    .build()
            );
        } else {
            responseWrapper.setData(t);
            responseWrapper.setLength(1);
        }
        return responseWrapper;
    }
}
