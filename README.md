# 🛒 MarketCali - Backend System

**MarketCali** es una plataforma de gestión para supermercados diseñada con una arquitectura de **microservicios** escalable, seguridad robusta y un frontend moderno. El sistema orquesta múltiples servicios para gestionar inventarios, ventas, autenticación y configuración centralizada.

---

## 🏗️ Arquitectura Técnica

El sistema implementa una arquitectura de microservicios basada en el ecosistema **Spring Cloud**, utilizando contenedores Docker para la orquestación.

### Diagrama de Comunicación
```mermaid
graph TD
    Client[Cliente Web/Mobile] --> Gateway[API Gateway (8088)]
    Gateway --> Auth[Auth Service (8081/8089)]
    Gateway --> Product[Product Service (8082)]
    Gateway --> Sales[Sales Service (8083)]
    
    Auth --> DB_Auth[(MySQL Auth DB)]
    Product --> DB_Product[(MySQL Product DB)]
    Sales --> DB_Sales[(MySQL Sales DB)]
    
    ServiceRegistry[Discovery Service (Eureka)] -.-> Gateway
    ServiceRegistry -.-> Auth
    ServiceRegistry -.-> Product
    ServiceRegistry -.-> Sales
```

### Componentes del Sistema

| Servicio | Puerto (Docker/Local) | Descripción Técnica |
| :--- | :--- | :--- |
| **Discovery Service** | `8761` | Servidor Eureka para el registro y descubrimiento dinámico de servicios. Permite el balanceo de carga del lado del cliente. |
| **API Gateway** | `8088` | Puerta de enlace basada en Spring Cloud Gateway. Maneja enrutamiento, CORS (`http://localhost:5173`) y seguridad perimetral. |
| **Auth Service** | `8081` / `8089` | Implementa seguridad `Spring Security` + `JWT`. Gestiona usuarios, roles y emisión de tokens. |
| **Product Service** | `8082` | CRUD de productos. Persistencia en MySQL (`marketcali_product_db`). |
| **Sales Service** | `8083` | Gestión de ventas y órdenes. Persistencia en MySQL (`marketcali_sales_db`). |
| **Config Service** | `8888` | (Opcional) Servidor de configuración centralizada para perfiles distribuidos. |
| **MySQL Database** | `3307` -> `3306` | Contenedor único de MySQL 8.0 que aloja múltiples esquemas (`auth`, `product`, `sales`). |

---

## 🚀 Tecnologías Clave

### Backend
*   **Java 17** (Eclipse Temurin)
*   **Spring Boot 3.2.5**
*   **Spring Cloud 2023.0.1** (Gateway, Netflix Eureka, Config)
*   **Spring Data JPA** (Hibernate)
*   **Spring Security** (JWT Authentication)
*   **Lombok**
*   **Maven**

### Frontend
*   **React 18**
*   **Vite**
*   **Axios** (Cliente HTTP)
*   **React Router Dom**
*   **TailwindCSS** (Estilizado)

### Infraestructura
*   **Docker & Docker Compose**
*   **MySQL 8.0**

---

## ⚙️ Configuración y Variables de Entorno

El proyecto utiliza `application.yml` para configuración por defecto y sobreescritura mediante variables de entorno en Docker.

### Variables Principales (Docker Compose)
Las siguientes variables se inyectan en los contenedores para la conexión entre servicios:

- `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`: URL del Discovery Service (`http://discovery-service:8761/eureka/`)
- `SPRING_DATASOURCE_URL`: Cadena de conexión JDBC (ej. `jdbc:mysql://mysql-db:3306/marketcali_product_db`)
- `SPRING_PROFILES_ACTIVE`: Perfil activo (usualmente `docker`).

### Puertos Expuestos
- **Frontend**: `http://localhost:80` (o `5173` en dev)
- **API Gateway**: `http://localhost:8088`
- **Eureka Dashboard**: `http://localhost:8761`
- **MySQL**: `localhost:3307` (Credenciales: `root` / `root` o `miguel` / `12345`)

---

## 🛠️ Despliegue y Ejecución

### Opción A: Docker Compose (Recomendado)

Levanta todo el ecosistema con un solo comando. Asegúrate de tener Docker corriendo.

```bash
docker-compose up -d --build
```

Esto iniciará:
1.  **MySQL** (y creará las BBDD automáticamente via `init.sql`).
2.  **Discovery Service**.
3.  **Config Service** (si activo).
4.  **Microservicios de Negocio** (Auth, Product, Sales).
5.  **API Gateway**.
6.  **Frontend**.

Accede a la aplicación en `http://localhost`.

### Opción B: Ejecución Manual (Desarrollo)

Si deseas correr los servicios individualmente para depuración:

1.  **Infraestructura Base**:
    ```bash
    # Iniciar MySQL (o usar docker solo para db)
    docker-compose up -d mysql-db
    ```

2.  **Discovery Service**:
    ```bash
    cd discovery-service && ./mvnw spring-boot:run
    ```

3.  **API Gateway & Servicios**:
    Iniciar cada uno en terminales separadas:
    ```bash
    cd api-gateway && ./mvnw spring-boot:run
    cd auth-service && ./mvnw spring-boot:run
    cd product-service && ./mvnw spring-boot:run
    cd sales-service && ./mvnw spring-boot:run
    ```

4.  **Frontend**:
    ```bash
    cd frontend && npm install && npm run dev
    ```

---

## 🔌 API Endpoints (Gateway: 8088)

Todas las peticiones deben dirigirse al API Gateway.

### 🔐 Auth Service (`/auth`)
*   `POST /auth/register`: Registro de usuarios.
*   `POST /auth/login`: Autenticación y obtención de Bearer Token.

### 📦 Product Service (`/api/productos`)
*   `GET /api/productos`: Listar catálogo.
*   `POST /api/productos`: Crear producto (Rol Admin/Empleado).
*   `PUT /api/productos/{id}`: Actualizar stock/precio.
*   `DELETE /api/productos/{id}`: Eliminar producto.

### 💰 Sales Service (`/sales` o `/api/sales`)
*   **Nota**: Revisar prefijo configurado en Gateway.
*   `POST /sales`: Registrar nueva venta.
*   `GET /sales/{id}`: Obtener detalle de venta.
*   `GET /sales/history`: Historial de ventas (por usuario/fecha).

---

## 👥 Gestión de Usuarios y Roles

El sistema utiliza **RBAC** (Role-Based Access Control).

| Rol | Permisos |
| :--- | :--- |
| **ADMIN** | Acceso total. Gestión de usuarios, configuración del sistema, reportes globales. |
| **EMPLEADO** | Gestión de inventario, registro de ventas, facturación. |
| **CLIENTE** | Visualización de productos, carrito de compras, historial propio. |

---

## 🤝 Contribución

1.  Hacer Fork del repositorio.
2.  Crear rama (`git checkout -b feature/NuevaFuncionalidad`).
3.  Commit (`git commit -m 'Implementar X'`).
4.  Push (`git push origin feature/NuevaFuncionalidad`).
5.  Crear Pull Request.

---
**Desarrollado para MarketCali**
