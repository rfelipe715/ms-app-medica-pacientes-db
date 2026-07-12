package cl.duoc.ms_pacientes_db.exception;

public class PacienteNotFoundException extends RuntimeException {

    public PacienteNotFoundException(Long id) {
        super("Paciente no encontrado con el id: " + id);
    }
}
