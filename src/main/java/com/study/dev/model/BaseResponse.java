package com.study.dev.model;

import lombok.Data;

@Data
public abstract class BaseResponse {
    private String result;
    private String message;
}
