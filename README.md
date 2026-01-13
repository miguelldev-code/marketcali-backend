# 🛒 MarketCali

**MarketCali** es una plataforma integral de gestión para supermercados diseñada con una arquitectura de **microservicios** escalable y un frontend moderno y reactivo. El sistema permite la administración eficiente de inventarios, ventas, facturación y usuarios, proporcionando una solución robusta para el entorno retail.

---

## 🏗️ Arquitectura del Sistema

El proyecto sigue una arquitectura de microservicios basada en **Spring Cloud**, lo que permite desacoplar la lógica de negocio en servicios independientes, facilitando el mantenimiento y la escalabilidad.

### Componentes Principales

-   **API Gateway**: Punto de entrada único para todas las peticiones del cliente. Enruta el tráfico a los servicios correspondientes (`auth-service`, `product-service`) y maneja preocupaciones transversales como CORS.
-   **Discovery Service (Eureka)**: Servidor de registro y descubrimiento de servicios, permitiendo que los microservicios se encuentren entre sí dinámicamente sin hardcodear URLs.
-   **Auth Service**: Encargado de la seguridad y gestión de usuarios. Maneja el registro, inicio de sesión y validación de credenciales (JWT).
-   **Product Service**: Gestiona el catálogo de productos, inventario y lógica relacionada con las mercancías.
-   **Config Service**: (Opcional/Futuro) Gestión centralizada de la configuración para todos los servicios.

---

## 🚀 Stack Tecnológico

### Backend (Microservicios)
-   **Lenguaje**: Java 17
-   **Framework**: Spring Boot 3.2.5
-   **Ecosistema Cloud**: Spring Cloud 2023.0.1 (Gateway, Netflix Eureka)
-   **Base de Datos**: MySQL / PostgreSQL (Configurable por servicio)
-   **Build Tool**: Maven

### Frontend (SPA)
-   **Framework**: React 18
-   **Build Tool**: Vite
-   **Ruting**: React Router Dom
-   **Cliente HTTP**: Axios
-   **Utilidades**: 
    -   `react-toastify` para notificaciones.
    -   `quagga` / `@ericblade/quagga2` para escaneo de códigos de barras.
    -   `react-icons` para iconografía.

---

## 📋 Prerrequisitos

Asegúrate de tener instalado en tu entorno:
-   **Java 17 JDK**
-   **Node.js 18+**
-   **Maven** (o usar el wrapper `mvnw` incluido)
-   **Docker** (Recomendado para bases de datos y servicios de infraestructura)

---

## 🛠️ Instalación y Ejecución

### 1. Clonar el Repositorio

```bash
git clone <url-del-repositorio>
cd marketcali-backend
```

### 2. Infraestructura Backend

Para levantar el ecosistema de microservicios, se recomienda seguir este orden de inicio:

1.  **Discovery Service**:
    ```bash
    cd discovery-service
    ./mvnw spring-boot:run
    ```
    *Espera a que inicie en el puerto 8761.*

2.  **API Gateway**:
    ```bash
    cd api-gateway
    ./mvnw spring-boot:run
    ```
    *Inicia en el puerto 8080.*

3.  **Servicios de Negocio (Auth, Product)**:
    Abre nuevas terminales para cada servicio:
    ```bash
    cd auth-service
    ./mvnw spring-boot:run
    ```
    ```bash
    cd product-service
    ./mvnw spring-boot:run
    ```

### 3. Frontend

Navega al directorio del frontend e inicia el servidor de desarrollo:

```bash
cd frontend
npm install
npm run dev
```
La aplicación estará disponible típicamente en `http://localhost:5173`.

---

## 🔌 Endpoints Principales

Las peticiones externas deben pasar a través del **API Gateway** (`http://localhost:8080`).

### Autenticación (`/auth`)
-   `POST /auth/register`: Registrar un nuevo usuario.
-   `POST /auth/login`: Iniciar sesión y obtener token.

### Productos (`/api/productos`)
-   `GET /api/productos`: Listar todos los productos.
-   `POST /api/productos`: Crear un nuevo producto (Requiere rol ADMIN/EMPLEADO).
-   `GET /api/productos/{id}`: Detalle de un producto.

---

## 👥 Roles de Usuario

El sistema implementa un control de acceso basado en roles (RBAC):

| Rol           | Descripción |
| :--- | :--- |
| **ADMIN** | Acceso total al sistema. Gestión de usuarios, configuración y reportes avanzados. |
| **EMPLEADO** | Gestión operativa. Ventas, inventario y facturación. |
| **USER/CLIENTE** | Acceso limitado. Visualización de catálogo y compras propias. |

---

## 🤝 Contribución

1.  Haz un Fork del proyecto.
2.  Crea tu rama de funcionalidad (`git checkout -b feature/AmazingFeature`).
3.  Haz Commit de tus cambios (`git commit -m 'Add some AmazingFeature'`).
4.  Haz Push a la rama (`git push origin feature/AmazingFeature`).
5.  Abre un Pull Request.

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - mira el archivo [LICENSE](LICENSE) para detalles.

---
**Desarrollado con ❤️ por Miguel Ángel Ortiz Escobar**
