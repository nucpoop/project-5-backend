package com.study.dev.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse<T> {
    private String result;
    private String message;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private HttpStatus httpStatus;
    private T data;
}
