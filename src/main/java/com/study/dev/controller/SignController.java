package com.study.dev.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.study.dev.model.Sign;
import com.study.dev.model.User;
import com.study.dev.security.JwtTokenProvider;
import com.study.dev.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping(value = "/signin")
    public Sign signInUser(HttpServletRequest request, @RequestBody User user){
        User result = (User)userService.loadUserByUsername(user.getEmail());
        Sign sign = new Sign();

        if(!passwordEncoder.matches(user.getPassword(), result.getPassword())){
            sign.setResult("FAIL");
            sign.setMessage("ID or Password is invalid");
            return sign;
        }

        List<String> roleList = result.getRoles();
        sign.setResult("SUCCESS");
        sign.setMessage("Login Complete");
        sign.setToken(jwtTokenProvider.createToken(result.getEmail(), roleList));
        return sign;
    }

    @PostMapping(value = "/signup")
    public Sign signUpUser(HttpServletRequest request, @RequestBody User user){
        User userForLogin = user;
        List<String> roles = new ArrayList<>();
        Sign sign = new Sign();

        roles.add("ROLE_USER");
        userForLogin.setRoles(roles);
        userForLogin.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean result = userService.joinUser(userForLogin);

        if(result){
            sign.setResult("SUCCESS");
            sign.setMessage("Join Complete");
        }else{
            sign.setResult("FAIL");
            sign.setMessage("Join Fail");
        }
        
        return sign;
    }
}
