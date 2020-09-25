package com.exemple.authentication.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "usuario_gestao")
public class UsuarioGestao {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "id_usuario_gestao", sequenceName = "gen_id_usuario_gestao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_usuario_gestao")
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

}
