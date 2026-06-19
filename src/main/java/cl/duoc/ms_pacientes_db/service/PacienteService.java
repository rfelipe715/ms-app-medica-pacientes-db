package cl.duoc.ms_pacientes_db.service;

import cl.duoc.ms_pacientes_db.model.entity.Paciente;
import cl.duoc.ms_pacientes_db.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    // C - Create (Crear)
    public Paciente registrarPaciente(Paciente paciente) {
        return repository.save(paciente);
    }

    // R - Read (Obtener la lista de todos los pacientes)
    public List<Paciente> obtenerTodos() {
        return repository.findAll();
    }

    // R - Read (Buscar un paciente específico por su ID)
    public Optional<Paciente> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // U - Update (Editar los datos de un paciente existente)
    public Paciente editarPaciente(Long id, Paciente datosNuevos) {
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
            return repository.save(paciente);
        }).orElseThrow(() -> new RuntimeException("Paciente no encontrado con el id: " + id));
    }

    // D - Delete (Eliminar un paciente de la base de datos)
    public void eliminarPaciente(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Paciente no encontrado con el id: " + id);
        }
    }
}