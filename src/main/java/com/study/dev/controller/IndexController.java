package com.study.dev.controller;

import java.time.LocalDate;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    
    @GetMapping("/index")
    public void healthCheck(HttpServletResponse response) throws Exception{
        Gson gson = new Gson();
        HashMap<String,String> map = new HashMap<String, String>();
        LocalDate localDate = LocalDate.now();

        map.put("currentTime", localDate.toString());
        response.getWriter().print(gson.toJson(map));
    }
}
