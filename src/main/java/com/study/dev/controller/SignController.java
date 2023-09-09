package com.study.dev.controller;

import com.study.dev.model.response.BaseResponse;
import com.study.dev.model.Sign;
import com.study.dev.model.User;
import com.study.dev.security.JwtTokenProvider;
import com.study.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class SignController {
    private final UserService userService;

    @PostMapping(value = "/sign-in")
    public ResponseEntity<BaseResponse<Sign>> signInUser(@RequestBody User user) {
        return userService.signInUser(user);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<BaseResponse<String>> signUpUser(@RequestBody User user) {
        return userService.signUpUser(user);
    }
}