package com.nuevospa.gestion.dto;

import java.util.Date;

public record DatosTareasOutDTO(Integer idTarea
        , String nombreTarea
        , String descripcionTarea
        , String estadoTarea
        , Date fechaRegistro
        , Date fechaActualizacion) {

}
