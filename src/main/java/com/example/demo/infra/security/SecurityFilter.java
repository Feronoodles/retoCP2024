package com.example.demo.infra.security;

import com.example.demo.persistence.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;





    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener el token del header
        var authHeader = request.getHeader("Authorization");


        if(authHeader!=null){
            var token = authHeader.replace("Bearer ","");

            var username = tokenService.getSubject(token);
            if(username!=null)
            {
                var user = userRepository.findByUsername(username);
                var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());//forzamos el inicio de sesion

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
