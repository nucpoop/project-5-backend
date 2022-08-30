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

import com.study.dev.model.BaseResponse;
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
    public ResponseEntity<BaseResponse<Sign>> signInUser(HttpServletRequest request, @RequestBody User user){
        BaseResponse<Sign> response = new BaseResponse<Sign>();
        User result = (User)userService.loadUserByUsername(user.getEmail());
        
        if(passwordEncoder.matches(user.getPassword(), result.getPassword())){
            response.setResult("success");
            response.setMessage("login complete");
            response.setData(
                Sign.builder()
                        .token(jwtTokenProvider.createToken(result.getEmail(), result.getRoles()))
                        .name(result.getName())
                        .email(result.getEmail())
                        .build()
            );
            
            return new ResponseEntity<BaseResponse<Sign>>(response, HttpStatus.OK);
        }else{
            response.setResult("unauthrized");
            response.setMessage("invalid password");
            response.setData(Sign.builder().build());

            return new ResponseEntity<BaseResponse<Sign>>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(value = "/signup")
    public ResponseEntity<BaseResponse<String>> signUpUser(HttpServletRequest request, @RequestBody User userForLogin){
        BaseResponse<String> response = new BaseResponse<>();

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        userForLogin.setRoles(roles);
        userForLogin.setPassword(passwordEncoder.encode(userForLogin.getPassword()));

        try {
            boolean result = userService.joinUser(userForLogin);

            if(result){
                response.setResult("success");
                response.setMessage("signup complete");
                response.setData(userForLogin.getEmail());

                return new ResponseEntity<BaseResponse<String>>(response, HttpStatus.OK);
            }else{
                response.setResult("fail");
                response.setMessage("Join Fail");
                return new ResponseEntity<BaseResponse<String>>(response, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            response.setResult("fail");
            response.setMessage(e.getMessage());
            return new ResponseEntity<BaseResponse<String>>(response, HttpStatus.CONFLICT);
        }
    }
}