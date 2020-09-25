package com.exemple.authentication.repository;

import com.exemple.authentication.model.entity.UsuarioGestao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioGestaoRepository extends JpaRepository<UsuarioGestao, Integer> {

    UsuarioGestao findByUsername(String username);

}
