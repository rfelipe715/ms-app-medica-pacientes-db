CREATE DATABASE IF NOT EXISTS hospital_pacientes_db;
USE hospital_pacientes_db;

CREATE TABLE pacientes (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           run VARCHAR(12) UNIQUE NOT NULL,
                           numero_registro VARCHAR(20),
                           numero_ficha_clinica VARCHAR(20) UNIQUE,
                           apellidos VARCHAR(100) NOT NULL,
                           nombres VARCHAR(100) NOT NULL,
                           sexo VARCHAR(20),
                           fecha_nacimiento DATE,
                           direccion VARCHAR(200),
                           telefono_contacto VARCHAR(20)
);