package com.study.dev.model;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

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

    @PrePersist
    public void initTime(){
        this.insertTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
