package com.study.dev.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.dev.model.Product;
import com.study.dev.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public void getProductsAll(HttpServletResponse response) throws Exception{
        List<Product> products = productService.getAllProducts();
        Gson gson = new Gson();

        response.getWriter().print(gson.toJson(products));
    }
}
