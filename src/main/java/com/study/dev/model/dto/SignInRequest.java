package com.study.dev.model.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
