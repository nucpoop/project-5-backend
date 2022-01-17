package com.study.dev.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Default {
    
    @Column(nullable = false)
    private Long insertId = 0L;

    private Date insertTime;

    private Long updateId = 0L;

    private Date updateTime;
}
