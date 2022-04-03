package com.adventureforge.gameservice.controllers.wrappers;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuccessResponseWrapper<T> {

    private T data;
    private String message;
    private int length;
    private int statusCode;
    private boolean paginated = false;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    PageInfo pageInfo;

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
}
