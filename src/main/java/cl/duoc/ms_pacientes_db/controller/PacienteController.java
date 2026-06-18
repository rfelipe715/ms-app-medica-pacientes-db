package cl.duoc.ms_pacientes_db.controller;

import cl.duoc.ms_pacientes_db.model.dto.PacienteDto;
import cl.duoc.ms_pacientes_db.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDto> registrarPaciente(@RequestBody PacienteDto dto) {
        PacienteDto nuevoPaciente = pacienteService.registrarPaciente(dto);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }
}