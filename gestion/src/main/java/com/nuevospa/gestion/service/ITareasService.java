package com.nuevospa.gestion.service;

import com.nuevospa.gestion.dto.ActualizarTareaDTO;
import com.nuevospa.gestion.dto.CrearTareaDTO;
import com.nuevospa.gestion.dto.DatosTareasOutDTO;

import java.util.List;

public interface ITareasService {

    List<DatosTareasOutDTO> consultarTareas();
    DatosTareasOutDTO crearTarea(CrearTareaDTO datosTareaInDTO);
    DatosTareasOutDTO actualizarTarea(ActualizarTareaDTO actualizarTareaDTO);
    void eliminar(Integer idTarea);

}
