package com.nuevospa.gestion.service.implent;

import com.nuevospa.gestion.dto.ActualizarTareaDTO;
import com.nuevospa.gestion.dto.CrearTareaDTO;
import com.nuevospa.gestion.dto.DatosTareasOutDTO;
import com.nuevospa.gestion.exception.GestionException;
import com.nuevospa.gestion.jpa.entity.EstadosTareasEntity;
import com.nuevospa.gestion.jpa.entity.TareasEntity;
import com.nuevospa.gestion.jpa.repository.EstadosTareasRepository;
import com.nuevospa.gestion.jpa.repository.TareasRepository;
import com.nuevospa.gestion.service.ITareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TareasService implements ITareasService {

    @Autowired
    private TareasRepository tareasRepository;
    @Autowired
    private EstadosTareasRepository estadosTareasRepository;

    public List<DatosTareasOutDTO> consultarTareas() {
        return tareasRepository.findAll().stream()
                .map(tareaEntity ->
                        new DatosTareasOutDTO(
                                tareaEntity.getIdTarea(),
                                tareaEntity.getNombreTarea(),
                                tareaEntity.getDescripcionTarea(),
                                tareaEntity.getEstadosTareasEntity().getEstado(),
                                tareaEntity.getFechaRegistro(),
                                tareaEntity.getFechaActualizacion())
                )
                .toList();
    }

    public DatosTareasOutDTO crearTarea(CrearTareaDTO datosTareaInDTO) {
        if (tareasRepository.findByNombreTarea(datosTareaInDTO.nombreTarea()).isPresent()) {
            throw new GestionException("La tarea ya existe.");
        }
        EstadosTareasEntity estadoActivo = estadosTareasRepository.findByEstado("Activo")
                .orElseThrow(() -> new GestionException("Estado inicial no configurado."));
        TareasEntity nuevaTarea = new TareasEntity();
        nuevaTarea.setNombreTarea(datosTareaInDTO.nombreTarea());
        nuevaTarea.setDescripcionTarea(datosTareaInDTO.descripcionTarea());
        nuevaTarea.setFechaRegistro(new Date());
        nuevaTarea.setEstadosTareasEntity(estadoActivo);
        TareasEntity savedEntity = tareasRepository.save(nuevaTarea);
        return new DatosTareasOutDTO(
                savedEntity.getIdTarea(),
                savedEntity.getNombreTarea(),
                savedEntity.getDescripcionTarea(),
                savedEntity.getEstadosTareasEntity().getEstado(),
                savedEntity.getFechaRegistro(),
                savedEntity.getFechaActualizacion()
        );
    }


    public DatosTareasOutDTO actualizarTarea(ActualizarTareaDTO actualizarTareaDTO) {
        Optional<TareasEntity> tareasEntity = tareasRepository.findById(actualizarTareaDTO.idTarea());
        if (!tareasEntity.isPresent()) {
            throw new GestionException("La tarea no existe.");
        }

        tareasEntity.get().setFechaActualizacion(new Date());
        EstadosTareasEntity estadosTareasEntity = estadosTareasRepository.findByEstado(actualizarTareaDTO.estadoTarea())
                .orElseThrow(() -> new GestionException("El estado enviado no existe."));

        tareasEntity.get().setEstadosTareasEntity(estadosTareasEntity);
        TareasEntity savedEntity = tareasRepository.save(tareasEntity.get());

        return new DatosTareasOutDTO(
                savedEntity.getIdTarea(),
                savedEntity.getNombreTarea(),
                savedEntity.getDescripcionTarea(),
                savedEntity.getEstadosTareasEntity().getEstado(),
                savedEntity.getFechaRegistro(),
                savedEntity.getFechaActualizacion()
        );
    }

    public void eliminar(Integer idTarea) {
        TareasEntity tareaAEliminar = tareasRepository.findById(idTarea)
                .orElseThrow(() -> new GestionException("La tarea no existe."));
        tareasRepository.delete(tareaAEliminar);
    }
}
