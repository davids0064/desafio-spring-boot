package com.nuevospa.gestion.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Setter
@Getter
@Cacheable(false)
public class UsuariosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "username")
    private String username;
    @Column(name = "primerNombre")
    private String primerNombre;
    @Column(name = "segundoNombre")
    private String segundoNombre;

}
