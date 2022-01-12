package com.study.dev.controller;

import javax.servlet.http.HttpServletRequest;

import com.study.dev.model.Sign;
import com.study.dev.model.User;
import com.study.dev.security.JwtTokenProvider;
import com.study.dev.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/signin")
    @ResponseBody
    public Sign signInUser(HttpServletRequest request, @RequestBody User user){
        //User result = (User)userService
        return null;
    }
}
