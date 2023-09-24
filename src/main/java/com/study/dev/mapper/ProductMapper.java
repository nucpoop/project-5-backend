package com.study.dev.mapper;

import com.study.dev.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM Pr_Product")
    List<Product> findAll();
}
