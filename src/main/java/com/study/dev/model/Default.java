package com.study.dev.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Default {
    private LocalDateTime insertTime;
    private LocalDateTime updateTime;

    @PrePersist
    public void initTime() {
        this.insertTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
