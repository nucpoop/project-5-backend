package com.study.dev.model.response;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private String result;
    private String message;
    private T data;
}
