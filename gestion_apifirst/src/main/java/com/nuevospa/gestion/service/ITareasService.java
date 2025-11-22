package com.nuevospa.gestion.service;

import com.nuevospa.gestion.model.ActualizarTareaDTO;
import com.nuevospa.gestion.model.CrearTareaDTO;
import com.nuevospa.gestion.model.DatosTareasOutDTO;

import java.util.List;

public interface ITareasService {

    List<DatosTareasOutDTO> consultarTareas();
    DatosTareasOutDTO crearTarea(CrearTareaDTO datosTareaInDTO);
    DatosTareasOutDTO actualizarTarea(ActualizarTareaDTO actualizarTareaDTO);
    void eliminar(Integer idTarea);

}
