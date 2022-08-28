# Spring Security with JWT



### Step 1:  Project Setup

Setup a simple project with spring security with an in memory or any user details provider.

---

### Step 2: Add dependency

```xml

		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.1</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->
		<dependency>
			<groupId>javax.xml.bind</groupId>
			<artifactId>jaxb-api</artifactId>
			<version>2.3.0</version>
		</dependency>


```

---

### Step 3: JWT Configuration

Create a jwt class in a package named like Util for the configuration of JWT.

```java
package com.jwt.JWT.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
```

### Step 4: Create auth end endpoint.

```java
    @PostMapping("/auth")
    public ResponseEntity<?> authLogin(@RequestBody AuthRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            throw new Exception("Username or password incorrect");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(new AuthResponse(jwt));
    }
```

For the above end point to work we need to autowire the JwtUtil class and authentication manager too.



*Create the AuthenticationManager function  in the security config class with extends webSecurityConfigurerAdapter*

```java
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {        
        return super.authenticationManager();
    }
```



Remeber to allow all request without security to the above endpoint.

```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {        
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/auth").permitAll()
            .anyRequest().authenticated()
            .and().formLogin()
            .and().httpBasic();
    }
```

---

### Step 4: Test endpoint.

Request

```json
{
	"username" : "admin",
	"password" : "admin"
}
```

Response

```jsonp
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY2MTI3MTE2MSwiaWF0IjoxNjYxMjM1MTYxfQ.KTRJmDDbppTlWU0ZUjWVuyXhUbC_NvgluuLMnPAmDr4"
}
```

---

 


