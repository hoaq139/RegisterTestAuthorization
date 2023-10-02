package com.example.registertestauthorization.security;

import com.example.registertestauthorization.entity.Account;
import com.example.registertestauthorization.model.TokenPayLoad;
import com.example.registertestauthorization.repository.AccountRepository;
import com.example.registertestauthorization.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final AccountRepository accountRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        String methodRequest = request.getMethod().toUpperCase();
        if(url.equals("/users") && methodRequest.equals("POST")){
            filterChain.doFilter(request, response);
            return ;
        }
        final String requestTokenHeader = request.getHeader("Authorization");

        String token = null;
        TokenPayLoad tokenPayLoad = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Token ")) {
            token = requestTokenHeader.substring(6).trim();

            try {

                tokenPayLoad = jwtTokenUtil.getTokenPayload(token);
            } catch (SignatureException e) {
                System.out.println("Invalid JWT signature");
            } catch (IllegalArgumentException ex) {
                System.out.println("Unable to get JWT");
            } catch (ExpiredJwtException ex) {
                System.out.println("Token has expired");
            }
        } else {
            System.out.println("JWT Token does not start with 'Token '");
        }
        if(tokenPayLoad != null && SecurityContextHolder.getContext().getAuthentication() == null){
            Optional<Account> accountOptional = accountRepository.findById(Integer.parseInt(tokenPayLoad.getUserId()));
            if(accountOptional.isPresent()){
                Account account = accountOptional.get();
                if(jwtTokenUtil.validate(token, account)){
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(account.getEmail(),
                            account.getPassword(), authorities);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, authorities
                    );
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
