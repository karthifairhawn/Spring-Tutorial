package com.seclearn.learnsecurity.Configurations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seclearn.learnsecurity.Entity.Users;
import com.seclearn.learnsecurity.Repository.UsersRepository;

@Service
public class MyUserDetailsServica implements UserDetailsService{

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {      
        Optional<Users> user =   usersRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return user.map(MyUserDetails::new).get();
    }
    
}
