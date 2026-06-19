-- Crear tabla de pacientes
CREATE TABLE IF NOT EXISTS paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    run VARCHAR(12) UNIQUE NOT NULL,
    numero_registro VARCHAR(50),
    numero_ficha_clinica VARCHAR(50),
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    sexo VARCHAR(20),
    fecha_nacimiento DATE,
    direccion TEXT,
    telefono_contacto VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear índices para mejora de consultas
CREATE INDEX idx_run ON paciente(run);
CREATE INDEX idx_numero_registro ON paciente(numero_registro);
CREATE INDEX idx_numero_ficha_clinica ON paciente(numero_ficha_clinica);
