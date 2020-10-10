package com.example.jpashop.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResponse {

    private Object data;
    private String code;
    private String defaultMessage;

    public static ApiResponse success(Object data) {
        return ApiResponse.builder()
                .data(data)
                .code("")
                .defaultMessage("")
                .build();
    }

    public static ApiResponse fail(
            String errorCode,
            String defaultMessage
    ){
        return ApiResponse.builder()
                .data("{}")
                .code(errorCode)
                .defaultMessage(defaultMessage)
                .build();
    }
}
