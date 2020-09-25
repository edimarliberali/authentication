package com.exemple.authentication.config.security;

import com.exemple.authentication.model.classes.UserAuthentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PortalAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public PortalAuthenticationToken(UserAuthentication userAuthentication) {
        super(userAuthentication.getUsername(), userAuthentication.getPassword());
    }

}
