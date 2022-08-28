package com.jwt.JWT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.JWT.Entity.MyUserDetails;
import com.jwt.JWT.Models.AuthRequest;
import com.jwt.JWT.Models.AuthResponse;
import com.jwt.JWT.Util.JwtUtil;

@RestController
public class Homepage {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/home")
    public String adminHome() {
        return "adminHome";
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authLogin(@RequestBody AuthRequest request) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new String("Usernam or password is incorrect."));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(new AuthResponse(jwt));
    }

}
