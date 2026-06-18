package cl.duoc.ms_pacientes_db.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 12)
    private String run;

    @Column(name = "numero_registro", length = 50)
    private String numeroRegistro;

    @Column(name = "numero_ficha_clinica", unique = true, length = 50)
    private String numeroFichaClinica;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(length = 20)
    private String sexo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(length = 255)
    private String direccion;

    @Column(name = "telefono_contacto", length = 20)
    private String telefonoContacto;
}
