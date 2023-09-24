package com.study.dev.controller;

import com.study.dev.model.dto.SignInRequest;
import com.study.dev.model.dto.SignUpRequest;
import com.study.dev.model.dto.SignUpResponse;
import com.study.dev.model.User;
import com.study.dev.model.response.BaseResponse;
import com.study.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class SignController {
    private final UserService userService;

    @PostMapping(value = "/sign-in")
    public ResponseEntity<BaseResponse<SignUpResponse>> signInUser(@RequestBody SignInRequest signInRequest) {
        return userService.signInUser(signInRequest);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<BaseResponse<String>> signUpUser(@RequestBody SignUpRequest signUpRequest) {
        return userService.signUpUser(signUpRequest);
    }
}