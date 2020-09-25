package com.exemple.authentication.config.interceptor;

import com.exemple.authentication.service.impl.GestaoUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GestaoAuthorizationHandler extends HandlerInterceptorAdapter {

    @Autowired
    private GestaoUserDetailsServiceImpl userDetailsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) return false;

        if (shouldNotFilter(request, handler)) return true;

        String username = "";

        if (username != null) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(username);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return true;
        }

        throw new BadCredentialsException("Please again login or contact service provider.");
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    private boolean shouldNotFilter(HttpServletRequest request, Object handlerMethod) {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        return new AntPathMatcher().match("/api/v1/gestao/login", request.getRequestURI());
    }

}