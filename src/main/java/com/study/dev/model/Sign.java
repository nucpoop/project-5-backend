package com.study.dev.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Sign {
    private String result;
    private String message;
    private String token;
}
