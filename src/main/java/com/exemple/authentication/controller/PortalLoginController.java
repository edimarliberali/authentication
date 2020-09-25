package com.exemple.authentication.controller;

import com.exemple.authentication.config.security.PortalAuthenticationToken;
import com.exemple.authentication.model.classes.UserAuthentication;
import com.exemple.authentication.model.classes.UserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PortalLoginController.PATH)
public class PortalLoginController {

    public final static String PATH = "/api/v1/portal/login";

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public UserTransfer authenticate(@RequestBody UserAuthentication userAuthentication) throws AuthenticationException {
        final User user = doLogin(userAuthentication);

        final UserTransfer transfer = new UserTransfer(user);
        return transfer;
    }

    private User doLogin(UserAuthentication userAuthentication) {
        final PortalAuthenticationToken authenticationToken = new PortalAuthenticationToken(userAuthentication);
        final Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return (User) authentication.getPrincipal();
    }

}
