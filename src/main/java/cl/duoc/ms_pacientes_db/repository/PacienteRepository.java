package cl.duoc.ms_pacientes_db.repository;

import cl.duoc.ms_pacientes_db.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Hereda automáticamente todos los métodos como save, findAll, findById, etc.
}
