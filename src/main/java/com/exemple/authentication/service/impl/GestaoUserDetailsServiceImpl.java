package com.exemple.authentication.service.impl;

import com.exemple.authentication.model.entity.UsuarioGestao;
import com.exemple.authentication.repository.UsuarioGestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("gestaoUserDetailsService")
public class GestaoUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioGestaoRepository usuarioGestaoRepository;

    @Override
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException("Username is required.");
        }

        final UsuarioGestao usuarioGestao = usuarioGestaoRepository.findByUsername(username);
        if (usuarioGestao == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new User(usuarioGestao.getUsername(), usuarioGestao.getPassword(), getGrantedAuthorities());
    }

    private List<GrantedAuthority> getGrantedAuthorities() {
        return Collections.emptyList();
    }

}
