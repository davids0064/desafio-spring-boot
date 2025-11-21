package com.nuevospa.gestion.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tareas")
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

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public EstadosTareasEntity getEstadosTareasEntity() {
        return estadosTareasEntity;
    }

    public void setEstadosTareasEntity(EstadosTareasEntity estadosTareasEntity) {
        this.estadosTareasEntity = estadosTareasEntity;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
