# ms-app-medica-pacientes-db

Capa **DB** del módulo **Pacientes**. Única capa con acceso a datos: expone un CRUD REST sobre la entidad `Paciente` y persiste en MySQL (`app_medica_pacientes`) vía Spring Data JPA + Hibernate.

| | |
|---|---|
| **Puerto** | `8083` |
| **Patrón** | Controller → Service → Repository (CSR) |
| **Ruta base** | `/api/pacientes` |
| **Persistencia** | MySQL `app_medica_pacientes` (JPA/Hibernate) |
| **Swagger** | `http://localhost:8083/swagger-ui.html` |

No llama a ningún otro microservicio: es el fondo de la pila del módulo Pacientes.

## Ejecución

```bash
# Con todo el ecosistema (recomendado), desde app-medica-et-fullstack-1/
docker compose up --build

# Individual
./mvnw spring-boot:run     # mvnw.cmd en Windows
./mvnw test
```
