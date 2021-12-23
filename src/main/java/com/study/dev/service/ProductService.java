package com.study.dev.service;

import java.util.List;

import com.study.dev.mapper.ProductMapper;
import com.study.dev.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductMapper productMapper;

    public List<Product> getAllProducts(){
        final List<Product> products = productMapper.findAll();
        return products;
    }
}
