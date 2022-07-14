package com.seclearn.learnsecurity.Configurations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.seclearn.learnsecurity.Entity.Users;

public class MyUserDetails implements UserDetails {

    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;
    private Boolean active;


    public MyUserDetails(Users user) {        
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.active = user.getIsEnabled();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        // System.out.println(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
