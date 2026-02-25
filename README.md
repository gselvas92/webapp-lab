# Device Inventory API (Spring Boot)

API REST para gestionar alta y mantenimiento de dispositivos de empresa:

- Ordenadores
- Servidores
- Switches
- Ratones
- Teclados

Cada dispositivo queda vinculado a un usuario y cada usuario a un departamento.

## Stack

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 (base de datos en memoria)

## Ejecutar

1. Instala Java 17 y Maven.
2. Lanza:

```bash
mvn spring-boot:run
```

API base URL: `http://localhost:8080`

Consola H2: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:inventorydb`
- User: `sa`
- Password: (vacío)

## Endpoints CRUD

### Departamentos

- `GET /api/departments`
- `GET /api/departments/{id}`
- `POST /api/departments`
- `PUT /api/departments/{id}`
- `DELETE /api/departments/{id}`

Ejemplo `POST /api/departments`:

```json
{
  "name": "IT"
}
```

### Usuarios

- `GET /api/users`
- `GET /api/users/{id}`
- `POST /api/users`
- `PUT /api/users/{id}`
- `DELETE /api/users/{id}`

Ejemplo `POST /api/users`:

```json
{
  "fullName": "Ana Perez",
  "email": "ana.perez@empresa.com",
  "departmentId": 1
}
```

### Dispositivos

- `GET /api/devices`
- `GET /api/devices?type=ORDENADOR` (filtro por tipo)
- `GET /api/devices/{id}`
- `POST /api/devices`
- `PUT /api/devices/{id}`
- `DELETE /api/devices/{id}`

Ejemplo `POST /api/devices`:

```json
{
  "assetTag": "PC-0001",
  "brand": "Dell",
  "model": "OptiPlex 7010",
  "type": "ORDENADOR",
  "ownerUserId": 1
}
```

Tipos soportados:

- `ORDENADOR`
- `SERVIDOR`
- `SWITCH`
- `RATON`
- `TECLADO`
