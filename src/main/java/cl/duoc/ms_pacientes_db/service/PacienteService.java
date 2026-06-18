package cl.duoc.ms_pacientes_db.service;

import cl.duoc.ms_pacientes_db.model.dto.PacienteDto;
import cl.duoc.ms_pacientes_db.model.entity.Paciente;
import cl.duoc.ms_pacientes_db.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteDto registrarPaciente(PacienteDto dto) {
        // Transformamos el DTO que viene de afuera en una Entidad para la BD
        Paciente paciente = new Paciente();
        paciente.setRun(dto.getRun());
        paciente.setNumeroRegistro(dto.getNumeroRegistro());
        paciente.setNumeroFichaClinica(dto.getNumeroFichaClinica());
        paciente.setNombres(dto.getNombres());
        paciente.setApellidos(dto.getApellidos());
        paciente.setSexo(dto.getSexo());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setDireccion(dto.getDireccion());
        paciente.setTelefonoContacto(dto.getTelefonoContacto());

        // Guardamos en la base de datos
        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        // Le asignamos el ID autogenerado de vuelta al DTO para confirmar el guardado
        dto.setId(pacienteGuardado.getId());
        return dto;
    }
}