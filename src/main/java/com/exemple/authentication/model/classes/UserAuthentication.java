package com.exemple.authentication.model.classes;

import lombok.Data;

@Data
public class UserAuthentication {

    private String username;

    private String password;

    public UserAuthentication() {
    }

    public UserAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
