package com.study.dev.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false) // 부모 클래스의 필드를 equals 함수와 hashcode 함수에 포함할지 여부
public class Sign extends BaseResponse {
    private String token;
}
