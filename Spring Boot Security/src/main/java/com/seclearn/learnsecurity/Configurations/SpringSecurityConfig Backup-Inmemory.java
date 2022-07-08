// package com.seclearn.learnsecurity.Configurations;

// import org.springframework.context.annotation.Bean;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @EnableWebSecurity
// public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         // super.configure(auth);
//         auth.inMemoryAuthentication()
//             .withUser("admin")
//             .password("shadow12")
//             .roles("Admin")
//             .and()
//             .withUser("user")
//             .password("shadow12")
//             .roles("User");
//     }

//     @Bean 
//     public PasswordEncoder getPasswordEncoder() {
//         return NoOpPasswordEncoder.getInstance();
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {        
//         http.authorizeRequests()
//             .antMatchers("/**").hasAnyRole("Admin").and().formLogin();
//     }

    

// }



