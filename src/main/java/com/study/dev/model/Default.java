package com.study.dev.model;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Default {
    private String insertId;
    private LocalDateTime insertTime;
    private String updateId;
    private LocalDateTime updateTime;
}
