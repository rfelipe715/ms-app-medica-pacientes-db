package cl.duoc.ms_pacientes_db.controller;

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
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // C - Create
    @PostMapping
    public ResponseEntity<PacienteDto> registrarPaciente(@Valid @RequestBody PacienteDto pacienteDto) {
        PacienteDto nuevoPaciente = pacienteService.registrarPaciente(pacienteDto);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }

    // R - Read (Obtener todos los pacientes)
    @GetMapping
    public ResponseEntity<List<PacienteDto>> obtenerTodos() {
        List<PacienteDto> pacientes = pacienteService.obtenerTodos();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    // R - Read (Obtener un paciente por su ID)
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> obtenerPorId(@PathVariable Long id) {
        return pacienteService.obtenerPorId(id)
                .map(paciente -> new ResponseEntity<>(paciente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // U - Update (Editar paciente existente)
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> editarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteDto datosNuevos) {
        PacienteDto pacienteActualizado = pacienteService.editarPaciente(id, datosNuevos);
        return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
    }

    // D - Delete (Eliminar paciente)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}