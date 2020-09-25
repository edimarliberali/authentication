package com.exemple.authentication.service.impl;

import com.exemple.authentication.model.entity.UsuarioPortal;
import com.exemple.authentication.repository.UsuarioPortalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("portalUserDetailsService")
public class PortalUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioPortalRepository usuarioPortalRepository;

    @Override
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException("Username is required.");
        }

        final UsuarioPortal usuarioPortal = usuarioPortalRepository.findByUsername(username);
        if (usuarioPortal == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new User(usuarioPortal.getUsername(), usuarioPortal.getPassword(), getGrantedAuthorities());
    }

    private List<GrantedAuthority> getGrantedAuthorities() {
        return Collections.emptyList();
    }

}
