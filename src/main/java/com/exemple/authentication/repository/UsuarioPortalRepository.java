package com.exemple.authentication.repository;

import com.exemple.authentication.model.entity.UsuarioPortal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioPortalRepository extends JpaRepository<UsuarioPortal, Integer> {

    UsuarioPortal findByUsername(String username);

}
