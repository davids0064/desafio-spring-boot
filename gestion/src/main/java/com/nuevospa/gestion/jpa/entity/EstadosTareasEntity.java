package com.nuevospa.gestion.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estados")
@Setter
@Getter
public class EstadosTareasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstado")
    private Integer idEstado;

    @Column(name = "estado")
    private String estado;

}
