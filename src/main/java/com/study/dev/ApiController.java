package com.study.dev;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @Autowired
    private DataService dataservice;

    @RequestMapping("/api")
    public List<ServerInfo> index(){
        return dataservice.getServerInfo();
    }
}
