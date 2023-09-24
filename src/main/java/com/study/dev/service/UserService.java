package com.study.dev.service;

import com.study.dev.model.dto.SignInRequest;
import com.study.dev.model.dto.SignUpResponse;
import com.study.dev.model.User;
import com.study.dev.model.dto.SignUpRequest;
import com.study.dev.model.response.BaseResponse;
import com.study.dev.model.response.ResultCode;
import com.study.dev.model.response.ResultMessage;
import com.study.dev.repository.UserRepository;
import com.study.dev.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<BaseResponse<SignUpResponse>> signInUser(SignInRequest signInRequest) {
        BaseResponse<SignUpResponse> response = new BaseResponse<>();
        User result = (User) userDetailsService.loadUserByUsername(signInRequest.getEmail());

        if (result == null) {
            response.setResult(ResultCode.FAIL);
            response.setMessage(ResultMessage.USER_NOT_FOUND);
            response.setData(SignUpResponse.builder().build());
            response.setHttpStatus(HttpStatus.UNAUTHORIZED);
        } else if (passwordEncoder.matches(signInRequest.getPassword(), result.getPassword())) {
            response.setResult(ResultCode.SUCCESS);
            response.setMessage(ResultMessage.LOGIN_COMPLETE);
            response.setData(
                    SignUpResponse.builder()
                            .token(jwtTokenProvider.createToken(result.getEmail(), result.getRoles()))
                            .name(result.getName())
                            .email(result.getEmail())
                            .build()
            );
            response.setHttpStatus(HttpStatus.OK);
        } else {
            response.setResult(ResultCode.UNAUTHORIZED);
            response.setMessage(ResultMessage.INVALID_PASSWORD);
            response.setData(SignUpResponse.builder().build());
            response.setHttpStatus(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    public ResponseEntity<BaseResponse<String>> signUpUser(SignUpRequest signUp) {
        BaseResponse<String> response = new BaseResponse<>();
        HttpStatus resultStatus = HttpStatus.NO_CONTENT;
        User user = new User(signUp);
        user.setRoles(List.of("USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            if (userRepository.save(user) != null) {
                response.setResult(ResultCode.SUCCESS);
                response.setMessage(ResultMessage.JOIN_COMPLETE);
                response.setData(user.getEmail());
                resultStatus = HttpStatus.OK;
            } else {
                response.setResult(ResultCode.FAIL);
                response.setMessage(ResultMessage.JOIN_FAIL);
                resultStatus = HttpStatus.NOT_MODIFIED;
            }
        } catch (Exception e) {
            response.setResult(ResultCode.FAIL);
            response.setMessage(e.getMessage());
            resultStatus = HttpStatus.NOT_MODIFIED;
        } finally {
            return new ResponseEntity<>(response, resultStatus);
        }
    }
}
