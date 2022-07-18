package com.jwt.JWT.Services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.JWT.Entity.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {        
        return new MyUserDetails("admin", "admin", true,new ArrayList<>());
    }
    
}
