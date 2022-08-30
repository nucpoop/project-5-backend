package com.study.dev.model;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private String result;
    private String message;
    private T data;
}
