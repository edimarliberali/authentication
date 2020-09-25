package com.exemple.authentication.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PortalAuthenticationProvider extends DaoAuthenticationProvider implements AuthenticationProvider {

    public PortalAuthenticationProvider(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    public AuthenticationProvider passwordEncoder(PasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
        return this;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PortalAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
