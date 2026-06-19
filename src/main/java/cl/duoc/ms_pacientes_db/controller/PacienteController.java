package cl.duoc.ms_pacientes_db.controller;

import cl.duoc.ms_pacientes_db.model.entity.Paciente;
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

    // C - Create (Ya lo teníamos)
    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        Paciente nuevoPaciente = pacienteService.registrarPaciente(paciente);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }

    // R - Read (Obtener todos los pacientes)
    @GetMapping
    public ResponseEntity<List<Paciente>> obtenerTodos() {
        List<Paciente> pacientes = pacienteService.obtenerTodos();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    // R - Read (Obtener un paciente por su ID)
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable Long id) {
        return pacienteService.obtenerPorId(id)
                .map(paciente -> new ResponseEntity<>(paciente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // U - Update (Editar paciente existente)
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> editarPaciente(@PathVariable Long id, @RequestBody Paciente datosNuevos) {
        try {
            Paciente pacienteActualizado = pacienteService.editarPaciente(id, datosNuevos);
            return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // D - Delete (Eliminar paciente)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        try {
            pacienteService.eliminarPaciente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}