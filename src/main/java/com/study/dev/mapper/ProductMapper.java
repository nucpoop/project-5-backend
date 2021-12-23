package com.study.dev.mapper;
import java.util.List;

import com.study.dev.model.Product;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    
    @Select("SELECT * FROM Pr_Product")
    List<Product> findAll();
}
