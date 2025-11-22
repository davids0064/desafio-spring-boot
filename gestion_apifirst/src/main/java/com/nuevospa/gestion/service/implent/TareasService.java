package com.nuevospa.gestion.service.implent;

import com.nuevospa.gestion.model.ActualizarTareaDTO;
import com.nuevospa.gestion.model.CrearTareaDTO;
import com.nuevospa.gestion.model.DatosTareasOutDTO;
import com.nuevospa.gestion.exception.GestionException;
import com.nuevospa.gestion.jpa.entity.EstadosTareasEntity;
import com.nuevospa.gestion.jpa.entity.TareasEntity;
import com.nuevospa.gestion.jpa.repository.EstadosTareasRepository;
import com.nuevospa.gestion.jpa.repository.TareasRepository;
import com.nuevospa.gestion.service.ITareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class TareasService implements ITareasService {

    @Autowired
    private TareasRepository tareasRepository;
    @Autowired
    private EstadosTareasRepository estadosTareasRepository;

    public List<DatosTareasOutDTO> consultarTareas() {
        List<DatosTareasOutDTO> salida = new ArrayList<>();
        tareasRepository.findAll().stream().forEach(tareaEntity -> {
            DatosTareasOutDTO datosTareasOutDTO = new DatosTareasOutDTO();
            datosTareasOutDTO.setIdTarea(tareaEntity.getIdTarea());
            datosTareasOutDTO.setNombreTarea(tareaEntity.getNombreTarea());
            datosTareasOutDTO.setDescripcionTarea(tareaEntity.getDescripcionTarea());
            datosTareasOutDTO.setEstadoTarea(tareaEntity.getEstadosTareasEntity().getEstado());
            datosTareasOutDTO.setFechaRegistro(convertDateToOffsetDateTime(tareaEntity.getFechaRegistro()));
            datosTareasOutDTO.setFechaActualizacion(convertDateToOffsetDateTime(tareaEntity.getFechaActualizacion()));
            salida.add(datosTareasOutDTO);
        });
        return salida;
    }

    public DatosTareasOutDTO crearTarea(CrearTareaDTO datosTareaInDTO) {
        if (tareasRepository.findByNombreTarea(datosTareaInDTO.getNombreTarea().toUpperCase(Locale.ROOT)).isPresent()) {
            throw new GestionException("La tarea ya existe.");
        }
        EstadosTareasEntity estadoActivo = estadosTareasRepository.findByEstado("Activo")
                .orElseThrow(() -> new GestionException("Estado inicial no configurado."));
        TareasEntity nuevaTarea = new TareasEntity();
        nuevaTarea.setNombreTarea(datosTareaInDTO.getNombreTarea().toUpperCase(Locale.ROOT));
        nuevaTarea.setDescripcionTarea(datosTareaInDTO.getDescripcionTarea());
        nuevaTarea.setFechaRegistro(new Date());
        nuevaTarea.setEstadosTareasEntity(estadoActivo);
        TareasEntity savedEntity = tareasRepository.save(nuevaTarea);
        DatosTareasOutDTO datosTareasOutDTO = new DatosTareasOutDTO();
        datosTareasOutDTO.setIdTarea(savedEntity.getIdTarea());
        datosTareasOutDTO.setNombreTarea(savedEntity.getNombreTarea());
        datosTareasOutDTO.setDescripcionTarea(savedEntity.getDescripcionTarea());
        datosTareasOutDTO.setEstadoTarea(savedEntity.getEstadosTareasEntity().getEstado());
        datosTareasOutDTO.setFechaRegistro(convertDateToOffsetDateTime(savedEntity.getFechaRegistro()));
        datosTareasOutDTO.setFechaActualizacion(convertDateToOffsetDateTime(savedEntity.getFechaActualizacion()));
        return datosTareasOutDTO;
    }


    public DatosTareasOutDTO actualizarTarea(ActualizarTareaDTO actualizarTareaDTO) {
        Optional<TareasEntity> tareasEntity = tareasRepository.findByNombreTarea(actualizarTareaDTO.getNombreTarea().toUpperCase(Locale.ROOT));
        if (!tareasEntity.isPresent()) {
            throw new GestionException("La tarea no existe.");
        }

        tareasEntity.get().setFechaActualizacion(new Date());
        EstadosTareasEntity estadosTareasEntity = estadosTareasRepository.findByEstado(actualizarTareaDTO.getEstadoTarea())
                .orElseThrow(() -> new GestionException("El estado enviado no existe."));

        tareasEntity.get().setEstadosTareasEntity(estadosTareasEntity);
        TareasEntity savedEntity = tareasRepository.save(tareasEntity.get());
        DatosTareasOutDTO datosTareasOutDTO = new DatosTareasOutDTO();
        datosTareasOutDTO.setIdTarea(savedEntity.getIdTarea());
        datosTareasOutDTO.setNombreTarea(savedEntity.getNombreTarea());
        datosTareasOutDTO.setDescripcionTarea(savedEntity.getDescripcionTarea());
        datosTareasOutDTO.setEstadoTarea(savedEntity.getEstadosTareasEntity().getEstado());
        datosTareasOutDTO.setFechaRegistro(convertDateToOffsetDateTime(savedEntity.getFechaRegistro()));
        datosTareasOutDTO.setFechaActualizacion(convertDateToOffsetDateTime(savedEntity.getFechaActualizacion()));
        return datosTareasOutDTO;
    }

    public void eliminar(Integer idTarea) {
        TareasEntity tareaAEliminar = tareasRepository.findById(idTarea)
                .orElseThrow(() -> new GestionException("La tarea no existe."));
        tareasRepository.delete(tareaAEliminar);
    }

    private OffsetDateTime convertDateToOffsetDateTime(Date date) {
        if (date == null) {
            return null;
        }
        // Utiliza la zona horaria del sistema para el mapeo
        return date.toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }
}
