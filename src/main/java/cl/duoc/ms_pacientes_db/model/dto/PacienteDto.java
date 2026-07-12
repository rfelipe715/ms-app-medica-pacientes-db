package cl.duoc.ms_pacientes_db.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteDto {

    private Long id;

    @NotBlank(message = "El RUN es obligatorio")
    @Pattern(regexp = "^[0-9]{7,8}-[0-9kK]{1}$", message = "El RUN debe tener el formato 12345678-9")
    private String run;

    private String numeroRegistro;

    private String numeroFichaClinica;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellidos;

    private String sexo;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimiento;

    private String direccion;

    private String telefonoContacto;
}