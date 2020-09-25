package com.exemple.authentication.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "usuario_portal")
public class UsuarioPortal {

    @Id
    @SequenceGenerator(name = "id_usuario_portal", sequenceName = "gen_id_usuario_portal", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_usuario_portal")
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

}