package cl.duoc.ms_pacientes_db.service;

import cl.duoc.ms_pacientes_db.exception.PacienteNotFoundException;
import cl.duoc.ms_pacientes_db.model.dto.PacienteDto;
import cl.duoc.ms_pacientes_db.model.entity.Paciente;
import cl.duoc.ms_pacientes_db.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    // --- Conversión Entity <-> DTO ---

    private PacienteDto convertirADto(Paciente paciente) {
        PacienteDto dto = new PacienteDto();
        dto.setId(paciente.getId());
        dto.setRun(paciente.getRun());
        dto.setNumeroRegistro(paciente.getNumeroRegistro());
        dto.setNumeroFichaClinica(paciente.getNumeroFichaClinica());
        dto.setNombres(paciente.getNombres());
        dto.setApellidos(paciente.getApellidos());
        dto.setSexo(paciente.getSexo());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setDireccion(paciente.getDireccion());
        dto.setTelefonoContacto(paciente.getTelefonoContacto());
        return dto;
    }

    private Paciente convertirAEntity(PacienteDto dto) {
        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setRun(dto.getRun());
        paciente.setNumeroRegistro(dto.getNumeroRegistro());
        paciente.setNumeroFichaClinica(dto.getNumeroFichaClinica());
        paciente.setNombres(dto.getNombres());
        paciente.setApellidos(dto.getApellidos());
        paciente.setSexo(dto.getSexo());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setDireccion(dto.getDireccion());
        paciente.setTelefonoContacto(dto.getTelefonoContacto());
        return paciente;
    }

    // C - Create
    public PacienteDto registrarPaciente(PacienteDto dto) {
        Paciente guardado = repository.save(convertirAEntity(dto));
        return convertirADto(guardado);
    }

    // R - Read (todos)
    public List<PacienteDto> obtenerTodos() {
        return repository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    // R - Read (por ID)
    public Optional<PacienteDto> obtenerPorId(Long id) {
        return repository.findById(id).map(this::convertirADto);
    }

    // U - Update
    public PacienteDto editarPaciente(Long id, PacienteDto datosNuevos) {
        return repository.findById(id).map(paciente -> {
            paciente.setRun(datosNuevos.getRun());
            paciente.setNumeroRegistro(datosNuevos.getNumeroRegistro());
            paciente.setNumeroFichaClinica(datosNuevos.getNumeroFichaClinica());
            paciente.setNombres(datosNuevos.getNombres());
            paciente.setApellidos(datosNuevos.getApellidos());
            paciente.setSexo(datosNuevos.getSexo());
            paciente.setFechaNacimiento(datosNuevos.getFechaNacimiento());
            paciente.setDireccion(datosNuevos.getDireccion());
            paciente.setTelefonoContacto(datosNuevos.getTelefonoContacto());
            return convertirADto(repository.save(paciente));
        }).orElseThrow(() -> new PacienteNotFoundException(id));
    }

    // D - Delete
    public void eliminarPaciente(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PacienteNotFoundException(id);

        }
    }
}