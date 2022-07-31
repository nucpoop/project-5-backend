package com.study.dev.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.dev.model.Sign;
import com.study.dev.model.User;
import com.study.dev.security.JwtTokenProvider;
import com.study.dev.service.UserService;

@RestController
@RequestMapping("/user")
public class SignController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/signin")
    public ResponseEntity<Sign> signInUser(HttpServletRequest request, @RequestBody User user){
        User result = (User)userService.loadUserByUsername(user.getEmail());
        
        if(!passwordEncoder.matches(user.getPassword(), result.getPassword())){
            Sign sign = Sign.builder().build();
            sign.setResult("FAIL");
            sign.setMessage("ID or Password is invalid");
            return new ResponseEntity<Sign>(sign, HttpStatus.UNAUTHORIZED);
        }else{
            Sign sign = Sign.builder()
                        .token(jwtTokenProvider.createToken(result.getEmail(), result.getRoles()))
                        .name(result.getName())
                        .email(result.getEmail())
                        .build();
            sign.setResult("SUCCESS");
            sign.setMessage("Login Complete");
            return new ResponseEntity<Sign>(sign, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/signup")
    public Sign signUpUser(HttpServletRequest request, @RequestBody User user){

        User userForLogin = user;
        List<String> roles = new ArrayList<>();
        Sign sign = Sign.builder().build();

        roles.add("ROLE_USER");
        userForLogin.setRoles(roles);
        userForLogin.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            boolean result = userService.joinUser(userForLogin);

            if(result){
                sign.setResult("SUCCESS");
                sign.setMessage("Join Complete");
            }else{
                sign.setResult("FAIL");
                sign.setMessage("Join Fail");
            }
        } catch (Exception e) {
            sign.setResult("FAIL");
            sign.setMessage(e.getMessage());
        }
        
        return sign;
    }
}
