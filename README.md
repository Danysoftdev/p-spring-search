# 🔍 Microservicio de Búsqueda de Productos - Spring Boot + MariaDB + Kubernetes

Este proyecto es un **microservicio de búsqueda de productos** desarrollado con **Spring Boot**. Su única funcionalidad es **buscar productos por su código** en una base de datos relacional **MariaDB**.  
Está preparado para desplegarse en un clúster **Kubernetes**, e incluye pruebas unitarias, de integración con **Testcontainers**, y generación de **reportes de cobertura** con **JaCoCo**.

---

## ⚙️ Características

- Búsqueda de productos por código (`/api/productos/obtener/{codigo}`)
- Integración con base de datos MariaDB
- Despliegue en Kubernetes con manifiestos YAML
- Pruebas unitarias (JUnit 5 + Mockito)
- Pruebas de integración con Testcontainers
- Reporte de cobertura de pruebas con JaCoCo

---

## 🧰 Tecnologías Utilizadas

- Java 17
- Spring Boot 3
- MariaDB
- Kubernetes (manifiestos YAML)
- JUnit 5
- Mockito
- Testcontainers
- JaCoCo

---

## 📦 Funcionalidad del Microservicio

| Método | Endpoint                          | Descripción                           |
|--------|-----------------------------------|---------------------------------------|
| `GET`  | `/api/productos/obtener/{codigo}` | Devuelve un producto según su código  |

---

## 🚀 Pruebas

- **Pruebas unitarias** del controlador y del servicio:

  ```bash
  ./mvnw test -Dtest="*ControllerTest,*ServiceTest"
  ````

- **Pruebas de integración** con base de datos usando Testcontainers:

  ```bash
  ./mvnw test -Dtest="*IT"
  ```

---

## 📊 Reporte de Cobertura

- **Generar reporte:**

  ```bash
  ./mvnw verify
  ```

- **Ver el reporte HTML:**
  Abre el archivo:

  ```
  target/site/jacoco/index.html
  ```

- **Ver en consola:**

  ```bash
  ./mvnw test -Pcoverage
  ```

---

## ☸️ Despliegue en Kubernetes

Todos los manifiestos YAML se encuentran en la carpeta:

```
k8s/
```

Incluyen:

* Namespace
* Deployment
* Service
* Ingress
* Secret
* Configuración para acceso a imagen de GHCR

> Se espera que la base de datos MariaDB esté ya desplegada en el clúster y accesible desde este microservicio.

---

## 👩‍💻 Autora

**Daniela Villalba Torres**
*Estudiante de Ingeniería de Software - Universidad EAM*

---

## 📄 Licencia

Uso académico. Libre para fines educativos y no comerciales.

