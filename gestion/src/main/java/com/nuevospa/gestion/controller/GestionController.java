package com.nuevospa.gestion.controller;

import com.nuevospa.gestion.dto.ActualizarTareaDTO;
import com.nuevospa.gestion.dto.CrearTareaDTO;
import com.nuevospa.gestion.dto.DatosTareasOutDTO;
import com.nuevospa.gestion.service.ITareasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gestionar-tareas")
@RequiredArgsConstructor
@Tag(name = "Gestión de Tareas", description = "Endpoints para el CRUD de tareas.")
public class GestionController {

    private final ITareasService tareasService;

    @GetMapping("consultar")
    @Operation(summary = "Lista todas las tareas registradas.")
    public List<DatosTareasOutDTO> consultarTareas() {
        return tareasService.consultarTareas();
    }

    @Operation(
            summary = "Registra una nueva tarea.",
            description = "Crea una tarea. Lanza 409 Conflict si el nombre de la tarea ya existe.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Tarea creada exitosamente.",
                            content = @Content(schema = @Schema(implementation = DatosTareasOutDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflicto: La tarea ya se encuentra registrada."
                    )
            }
    )
    @PostMapping("crear")
    public ResponseEntity<DatosTareasOutDTO> crearTarea(@RequestBody CrearTareaDTO datosTareaInDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareasService.crearTarea(datosTareaInDTO));
    }

    @Operation(
            summary = "Registra una nueva tarea.",
            description = "Crea una tarea. Lanza 404 Conflict si el nombre de la tarea ya existe.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tarea creada exitosamente.",
                            content = @Content(schema = @Schema(implementation = DatosTareasOutDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No encontrado: La tarea con el ID especificado no existe."
                    )
            }
    )
    @PutMapping("actualizar")
    public ResponseEntity<DatosTareasOutDTO> actualizar(@RequestBody ActualizarTareaDTO actualizarTareaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(tareasService.actualizarTarea(actualizarTareaDTO));
    }

    @Operation(
            summary = "Elimina una tarea por su ID.",
            description = "Eliminación permanente de la tarea.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Tarea eliminada exitosamente (No Content)."),
                    @ApiResponse(responseCode = "404", description = "No encontrado: La tarea con el ID especificado no existe.")
            }
    )
    @DeleteMapping("eliminar/{idTarea}")
    public ResponseEntity<Void> eliminar(@PathVariable() Integer idTarea) {
        tareasService.eliminar(idTarea);
        return ResponseEntity.noContent().build();
    }

}
