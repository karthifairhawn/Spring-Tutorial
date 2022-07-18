package com.jwt.JWT.Entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDetails implements UserDetails{

    String username;
    String password;
    boolean active;
    List authorities;
    
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
        
        return username;
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
