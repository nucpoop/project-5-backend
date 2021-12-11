package com.study.dev.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public void testApi(HttpServletResponse response) throws Exception{
        Gson gson = new Gson();

        HashMap<String,String> map = new HashMap<String, String>();
        map.put("test", "Hello World");
        response.getWriter().print(gson.toJson(map));
    }
    
}
