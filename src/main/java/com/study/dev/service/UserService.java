package com.study.dev.service;

import com.study.dev.exception.UserNotFoundException;
import com.study.dev.model.response.BaseResponse;
import com.study.dev.model.Sign;
import com.study.dev.model.User;
import com.study.dev.model.response.ResultCode;
import com.study.dev.model.response.ResultMessage;
import com.study.dev.repository.UserRepository;
import com.study.dev.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<BaseResponse<Sign>> signInUser(User user) {
        BaseResponse<Sign> response = new BaseResponse<>();
        HttpStatus resultStatus;

        User result = (User) this.loadUserByUsername(user.getEmail());

        if (passwordEncoder.matches(user.getPassword(), result.getPassword())) {
            response.setResult(ResultCode.SUCCESS);
            response.setMessage(ResultMessage.LOGIN_COMPLETE);
            response.setData(
                    Sign.builder()
                            .token(jwtTokenProvider.createToken(result.getEmail(), result.getRoles()))
                            .name(result.getName())
                            .email(result.getEmail())
                            .build()
            );
            resultStatus = HttpStatus.OK;
        } else {
            response.setResult(ResultCode.UNAUTHORIZED);
            response.setMessage(ResultMessage.INVALID_PASSWORD);
            response.setData(Sign.builder().build());
            resultStatus = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(response, resultStatus);
    }

    public ResponseEntity<BaseResponse<String>> signUpUser(User user) {
        BaseResponse<String> response = new BaseResponse<>();
        HttpStatus resultStatus = HttpStatus.NO_CONTENT;
        user.setRoles(List.of("ROLE_USER"));
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
        }finally {
            return new ResponseEntity<>(response, resultStatus);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findByEmail(id).orElseThrow(UserNotFoundException::new);
    }
}
