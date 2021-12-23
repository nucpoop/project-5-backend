package com.study.dev.model;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class Product {
    private String productCode;
    private int rIdx;
    private String sizeCode;
    private String colorCode;
    private int price;
    private String explanation;
    private String dispYn;
    private String useYn;
    private int insNo;
    private Date insDate;
    private int uptNo;
    private Date uptDate; 
}
