package com.study.dev.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.dev.model.Product;
import com.study.dev.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final ProductService productService;

    public TestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public void getProductsAll(HttpServletResponse response) throws Exception{
        List<Product> products = productService.getAllProducts();
        Gson gson = new Gson();

        response.getWriter().print(gson.toJson(products));
    }

    @GetMapping("/test")
    public void testApi(HttpServletResponse response) throws Exception{
        Gson gson = new Gson();

        HashMap<String,String> map = new HashMap<>();
        map.put("test", "Hello World");
        response.getWriter().print(gson.toJson(map));
    }
    
}
