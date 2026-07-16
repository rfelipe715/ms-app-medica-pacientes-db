package cl.duoc.ms_pacientes_db.service;

import cl.duoc.ms_pacientes_db.exception.PacienteNotFoundException;
import cl.duoc.ms_pacientes_db.model.dto.PacienteDto;
import cl.duoc.ms_pacientes_db.model.entity.Paciente;
import cl.duoc.ms_pacientes_db.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @Test
    void obtenerPorId_deberiaRetornarPaciente_cuandoExiste() {

        Long id = 1L;
        Paciente pacienteEntity = new Paciente();
        pacienteEntity.setId(id);
        pacienteEntity.setRun("12345678-9");
        pacienteEntity.setNombres("Juan");
        pacienteEntity.setApellidos("Pérez");
        pacienteEntity.setFechaNacimiento(LocalDate.of(1990, 5, 20));

        when(pacienteRepository.findById(id)).thenReturn(Optional.of(pacienteEntity));

        Optional<PacienteDto> resultado = pacienteService.obtenerPorId(id);


        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getNombres());
        assertEquals("Pérez", resultado.get().getApellidos());
        assertEquals(id, resultado.get().getId());

        verify(pacienteRepository, times(1)).findById(id);
    }
    @Test
    void obtenerPorId_deberiaRetornarVacio_cuandoNoExiste() {
        // Given
        Long id = 99L;
        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<PacienteDto> resultado = pacienteService.obtenerPorId(id);

        // Then
        assertFalse(resultado.isPresent());
        verify(pacienteRepository, times(1)).findById(id);
    }
    @Test
    void registrarPaciente_deberiaGuardarYRetornarPaciente() {
        // Given
        PacienteDto dtoEntrada = new PacienteDto();
        dtoEntrada.setRun("11111111-1");
        dtoEntrada.setNombres("María");
        dtoEntrada.setApellidos("López");
        dtoEntrada.setFechaNacimiento(LocalDate.of(1985, 3, 10));

        Paciente pacienteGuardado = new Paciente();
        pacienteGuardado.setId(1L);
        pacienteGuardado.setRun("11111111-1");
        pacienteGuardado.setNombres("María");
        pacienteGuardado.setApellidos("López");
        pacienteGuardado.setFechaNacimiento(LocalDate.of(1985, 3, 10));

        when(pacienteRepository.save(any(Paciente.class))).thenReturn(pacienteGuardado);

        // When
        PacienteDto resultado = pacienteService.registrarPaciente(dtoEntrada);

        // Then
        assertNotNull(resultado.getId());
        assertEquals("María", resultado.getNombres());
        assertEquals("11111111-1", resultado.getRun());

        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }
    @Test
    void editarPaciente_deberiaActualizarDatos_cuandoExiste() {
        // Given
        Long id = 1L;
        Paciente pacienteExistente = new Paciente();
        pacienteExistente.setId(id);
        pacienteExistente.setRun("22222222-2");
        pacienteExistente.setNombres("Carlos");
        pacienteExistente.setApellidos("Soto");
        pacienteExistente.setFechaNacimiento(LocalDate.of(1980, 1, 1));

        PacienteDto datosNuevos = new PacienteDto();
        datosNuevos.setRun("22222222-2");
        datosNuevos.setNombres("Carlos Alberto");
        datosNuevos.setApellidos("Soto Muñoz");
        datosNuevos.setFechaNacimiento(LocalDate.of(1980, 1, 1));

        when(pacienteRepository.findById(id)).thenReturn(Optional.of(pacienteExistente));
        when(pacienteRepository.save(any(Paciente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        PacienteDto resultado = pacienteService.editarPaciente(id, datosNuevos);

        // Then
        assertEquals("Carlos Alberto", resultado.getNombres());
        assertEquals("Soto Muñoz", resultado.getApellidos());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }
    @Test
    void eliminarPaciente_deberiaEliminar_cuandoExiste() {
        // Given
        Long id = 1L;
        when(pacienteRepository.existsById(id)).thenReturn(true);

        // When
        pacienteService.eliminarPaciente(id);

        // Then
        verify(pacienteRepository, times(1)).existsById(id);
        verify(pacienteRepository, times(1)).deleteById(id);
    }
    @Test
    void eliminarPaciente_deberiaLanzarExcepcion_cuandoNoExiste() {
        // Given
        Long id = 99L;
        when(pacienteRepository.existsById(id)).thenReturn(false);

        // When & Then
        assertThrows (PacienteNotFoundException.class, () -> {
            pacienteService.eliminarPaciente(id);
        });

        verify(pacienteRepository, never()).deleteById(any());
    }
    @Test
    void obtenerTodos_deberiaRetornarListaDePacientes() {
        // Given
        Paciente paciente1 = new Paciente();
        paciente1.setId(1L);
        paciente1.setNombres("Ana");
        paciente1.setApellidos("Ramírez");

        Paciente paciente2 = new Paciente();
        paciente2.setId(2L);
        paciente2.setNombres("Pedro");
        paciente2.setApellidos("Díaz");

        when(pacienteRepository.findAll()).thenReturn(java.util.List.of(paciente1, paciente2));

        // When
        var resultado = pacienteService.obtenerTodos();

        // Then
        assertEquals(2, resultado.size());
        assertEquals("Ana", resultado.get(0).getNombres());
        assertEquals("Pedro", resultado.get(1).getNombres());
        verify(pacienteRepository, times(1)).findAll();
    }
    @Test
    void editarPaciente_deberiaLanzarExcepcion_cuandoNoExiste() {
        // Given
        Long id = 99L;
        PacienteDto datosNuevos = new PacienteDto();
        datosNuevos.setNombres("Cualquiera");

        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(PacienteNotFoundException.class, () -> {
            pacienteService.editarPaciente(id, datosNuevos);
        });

        verify(pacienteRepository, never()).save(any());
    }
}
