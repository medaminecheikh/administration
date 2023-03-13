package com.administration.security.filters;

import com.administration.security.Jwt.JwtVariables;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        UsernamePasswordAuthenticationToken AuthenticationToken=
                new UsernamePasswordAuthenticationToken(username,password);
        log.info("Attemp authentication  !!!!!!!   "  +username );
        return authenticationManager.authenticate(AuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user=(User) authResult.getPrincipal();
        Algorithm algorithm=Algorithm.HMAC256(JwtVariables.SECRET);

        //create access token
        String jwtAccesToken= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtVariables.EXPIRE_ACCESS))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",user.getAuthorities().stream().map(
                        grantedAuthority -> grantedAuthority.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm);

        //create refresh token
        String jwtRefreshToken= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtVariables.EXPIRE_REFRESH))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Map<String,String> idToken= new HashMap<>();
        idToken.put("Access-token",jwtAccesToken);
        idToken.put("Refresh-token",jwtRefreshToken);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(),idToken);
        log.info("Successful authentication");

    }


}
