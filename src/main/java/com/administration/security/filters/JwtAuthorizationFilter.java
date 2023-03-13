package com.administration.security.filters;

import com.administration.security.Jwt.JwtVariables;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().startsWith("/POS/auth/")) {
            log.info("!!!!!!!!!/refreshtoken /LOGIN!!!! started");
            filterChain.doFilter(request, response);
            log.info("!!!!!!!!!/refreshtoken!!!! finished");

        } else {
            String authorizationtoken = request.getHeader(JwtVariables.AUTH_HEADER);
            if (authorizationtoken != null && authorizationtoken.startsWith(JwtVariables.PREFIX)) {
                try {
                    log.info("!!!!!!!!!token not null!!!! and prefix Bearer found");

                    String jwt = authorizationtoken.substring(7);
                    Algorithm algorithm = Algorithm.HMAC256(JwtVariables.SECRET);
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                    String username = decodedJWT.getSubject();
                    List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
                    log.info("!!!!!!!!!"+ username +"!!!!!!!!!");
                    log.info(roles.toString());
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    roles.forEach(rn -> {
                        authorities.add(new SimpleGrantedAuthority(rn));
                    });
                    log.info("{}",authorities);
                    UsernamePasswordAuthenticationToken AuthenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    log.info(username.toString());
                    log.info("{}",authorities);
                    SecurityContextHolder.getContext().setAuthentication(AuthenticationToken);
                    filterChain.doFilter(request, response);
                    log.info("!!!!!!!!!Token found!!!! end");
                    log.info("{}");
                } catch (Exception e) {
                    response.setHeader("error message", e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            } else filterChain.doFilter(request, response);



        }
    }
}
