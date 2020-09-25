package com.exemple.authentication.config.security;

import com.exemple.authentication.model.classes.UserAuthentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class GestaoAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public GestaoAuthenticationToken(UserAuthentication userAuthentication) {
        super(userAuthentication.getUsername(), userAuthentication.getPassword());
    }

}
