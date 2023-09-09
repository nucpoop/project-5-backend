package com.study.dev.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class IndexController {
    
    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheckEntity() {
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();

        map.put("currentTime", new SimpleDateFormat("YYYY/MM/dd HH:mm:ss").format(System.currentTimeMillis()));
        return new ResponseEntity<>(gson.toJson(map), HttpStatus.OK);
    }
}
