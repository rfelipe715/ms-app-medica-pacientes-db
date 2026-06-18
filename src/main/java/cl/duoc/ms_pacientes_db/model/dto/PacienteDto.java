package cl.duoc.ms_pacientes_db.model.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PacienteDto {
    private Long id;
    private String run;
    private String numeroRegistro;
    private String numeroFichaClinica;
    private String nombres;
    private String apellidos;
    private String sexo;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefonoContacto;
}