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
    private LocalDateTime insertDateTime;
    private LocalDateTime updateDateTime;

    @PrePersist
    public void initTime() {
        this.insertDateTime = LocalDateTime.now();
        this.updateDateTime = LocalDateTime.now();
    }
}
