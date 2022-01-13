package com.study.dev.service;

import com.study.dev.exception.UserNotFoundException;
import com.study.dev.model.User;
import com.study.dev.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {       
        return userRepo.findByEmail(id).orElseThrow(UserNotFoundException::new);
    }

    public UserDetails findByName(String name){
        return userRepo.findByName(name).orElseThrow(UserNotFoundException::new);
    }

    public boolean joinUser(User join){
        if(userRepo.save(join) != null){
            return true;
        }else{
            return false;
        }
    }
}
