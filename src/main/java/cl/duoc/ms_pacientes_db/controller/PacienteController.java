package cl.duoc.ms_pacientes_db.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import cl.duoc.ms_pacientes_db.model.dto.PacienteDto;
import cl.duoc.ms_pacientes_db.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Pacientes", description = "Gestión de pacientes del sistema hospitalario")

public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // C - Create
    @Operation(summary = "Registrar un nuevo paciente", description = "Crea un paciente nuevo en el sistema, validando que el RUN tenga formato correcto y los campos obligatorios estén presentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos, revisar formato de RUN y campos obligatorios")
    })
    @PostMapping
    public ResponseEntity<PacienteDto> registrarPaciente(@Valid @RequestBody PacienteDto pacienteDto) {
        PacienteDto nuevoPaciente = pacienteService.registrarPaciente(pacienteDto);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }

    // R - Read (Obtener todos los pacientes)
        @Operation(summary = "Obtener todos los pacientes", description = "Retorna la lista completa de pacientes registrados en el sistema.")
        @ApiResponse(responseCode = "200", description = "Lista de pacientes obtenida exitosamente")
        @GetMapping public ResponseEntity<List<PacienteDto>> obtenerTodos() {
        List<PacienteDto> pacientes = pacienteService.obtenerTodos();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    // R - Read (Obtener un paciente por su ID)
    @Operation(summary = "Buscar paciente por ID", description = "Retorna los datos de un paciente específico según su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe un paciente con el ID indicado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> obtenerPorId(@PathVariable Long id) {
        return pacienteService.obtenerPorId(id)
                .map(paciente -> new ResponseEntity<>(paciente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // U - Update (Editar paciente existente)
    @Operation(summary = "Editar un paciente existente", description = "Actualiza los datos de un paciente ya registrado, identificado por su ID. Valida que los nuevos datos cumplan las reglas de negocio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
            @ApiResponse(responseCode = "404", description = "No existe un paciente con el ID indicado")
    })
    @PutMapping("/{id}") public ResponseEntity<PacienteDto> editarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteDto datosNuevos) {
        PacienteDto pacienteActualizado = pacienteService.editarPaciente(id, datosNuevos);
        return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
    }

    // D - Delete (Eliminar paciente)
    @Operation(summary = "Eliminar un paciente", description = "Elimina de forma permanente un paciente del sistema, identificado por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente eliminado exitosamente, sin contenido de respuesta"),
            @ApiResponse(responseCode = "404", description = "No existe un paciente con el ID indicado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}