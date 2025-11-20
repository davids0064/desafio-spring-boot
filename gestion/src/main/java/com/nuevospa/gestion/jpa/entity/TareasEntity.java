package com.nuevospa.gestion.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tareas")
@Setter
@Getter
@Cacheable(false)
public class TareasEntity implements Serializable {

    private static final long serialzable = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idTarea;

    @Column(name = "nombre")
    private String nombreTarea;
    @Column(name = "descripcion")
    private String descripcionTarea;
    @JoinColumn(name = "estado")
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    private EstadosTareasEntity estadosTareasEntity;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaActualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

}
