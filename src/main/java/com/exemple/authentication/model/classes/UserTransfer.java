package com.exemple.authentication.model.classes;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserTransfer {

    private String username;

    private List<String> roles;

    public UserTransfer(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public UserTransfer(User userDetails) {
        this(userDetails.getUsername(), null);
        this.roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::toString)
                .collect(Collectors.toList());
    }

}
