package com.nuevospa.gestion.controller;

import com.nuevospa.gestion.dto.ActualizarTareaDTO;
import com.nuevospa.gestion.dto.CrearTareaDTO;
import com.nuevospa.gestion.dto.DatosTareasOutDTO;
import com.nuevospa.gestion.service.ITareasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gestionar-tareas")
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Gestión de Tareas", description = "Endpoints para el CRUD de tareas.")
public class GestionController {

    @Autowired
    private ITareasService tareasService;

    @GetMapping
    @Operation(
            summary = "Lista todas las tareas registradas.",
            description = "Se debe utilizar uno de los usuarios precargados que se encuentran dentro del README_DOCKER.md, una vez enviados los datos, genera un token el cual tiene una vigencia de 60 minutos, dicho token se debe utilizar para autenticarse en los demás servicios rest ingresandolo en el candado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consulta exitosa.",
                            content = @Content(schema = @Schema(implementation = DatosTareasOutDTO.class))
                    )
            }
    )
    public List<DatosTareasOutDTO> consultarTareas() {
        return tareasService.consultarTareas();
    }

    @Operation(
            summary = "Registra una nueva tarea.",
            description = "El servicio recibe el nombre de la tarea y la descripción, colocando por defecto el estado Activo",
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
    @PostMapping
    public ResponseEntity<DatosTareasOutDTO> crearTarea(@RequestBody CrearTareaDTO datosTareaInDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareasService.crearTarea(datosTareaInDTO));
    }

    @Operation(
            summary = "Actualiza una nueva tarea.",
            description = "El servicio recibe el nombre de la tarea y el nuevo estado, los estados precargados son Activo e Inactivo",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tarea actualizada exitosamente.",
                            content = @Content(schema = @Schema(implementation = DatosTareasOutDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No encontrado: La tarea con el ID especificado no existe."
                    )
            }
    )
    @PutMapping
    public ResponseEntity<DatosTareasOutDTO> actualizar(@RequestBody ActualizarTareaDTO actualizarTareaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(tareasService.actualizarTarea(actualizarTareaDTO));
    }

    @Operation(
            summary = "Elimina una tarea por su ID.",
            description = "El servicio recibe como parametro el id de la tarea.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Tarea eliminada exitosamente (No Content)."),
                    @ApiResponse(responseCode = "404", description = "No encontrado: La tarea con el ID especificado no existe.")
            }
    )
    @DeleteMapping("/{idTarea}")
    public ResponseEntity<Void> eliminar(@PathVariable() Integer idTarea) {
        tareasService.eliminar(idTarea);
        return ResponseEntity.noContent().build();
    }

}
