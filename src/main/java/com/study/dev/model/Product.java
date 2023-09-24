package com.study.dev.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Product {
    private String productCode;
    private int index;
    private String sizeCode;
    private String colorCode;
    private int price;
    private String explanation;
    private String displayYn;
    private String useYn;
    private int insertNo;
    private Date insertDateTime;
    private int updateNo;
    private Date updateDateTime;
}
