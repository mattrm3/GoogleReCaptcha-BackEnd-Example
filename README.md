
# 🛡️ Backend - Ejemplo de Google reCAPTCHA

Este proyecto es un **backend en Java con Spring Boot** que implementa verificación de **Google reCAPTCHA** para proteger formularios y endpoints contra bots y accesos automatizados.

---

## 🚀 Características

- ✅ API REST para verificar tokens de Google reCAPTCHA.
- 🌐 Configuración de CORS (por defecto `http://localhost:4321`) para integrarse fácilmente con el frontend.
- 🔐 Compatible con Google reCAPTCHA **v2** y **v3**.

---

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/captchaexample/backend/
│   │       ├── BackendApplication.java
│   │       ├── config/
│   │       │   └── CorsConfig.java
│   │       └── controller/
│   │           └── CaptchaController.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/captchaexample/backend/
        └── BackendApplicationTests.java
```

- **BackendApplication.java**: Punto de entrada de la aplicación Spring Boot.
- **CorsConfig.java**: Configuración de CORS para habilitar peticiones desde el frontend.
- **CaptchaController.java**: Controlador REST que verifica el token de reCAPTCHA.
- **application.properties**: Configuración general de la aplicación.

---

## ⚙️ Instalación y Ejecución

1. **Clona el repositorio**

```bash
git clone https://github.com/tu-usuario/tu-repo.git
cd backend
```

2. **Agrega tu clave secreta de reCAPTCHA**

Edita el archivo `CaptchaController.java` y reemplaza:

```java
private static final String SECRET_KEY = "TU_SECRET_KEY";
```

con tu clave secreta obtenida en [Google reCAPTCHA Admin Console](https://www.google.com/recaptcha/admin).

3. **Compila y ejecuta**

Con el wrapper incluido:

```bash
./mvnw spring-boot:run
```

O con Maven instalado globalmente:

```bash
mvn spring-boot:run
```

---

## 🧪 Prueba del Endpoint

Haz una solicitud POST a:

```
POST http://localhost:8080/api/verify-captcha
Content-Type: application/x-www-form-urlencoded

captcha=<TOKEN_DEL_FRONTEND>
```

Donde `<TOKEN_DEL_FRONTEND>` es el token generado por el reCAPTCHA del frontend.

---

## 🧪 Ejecutar Tests

Incluye una prueba básica para verificar el contexto de la aplicación:

```bash
./mvnw test
```

---

## ⚠️ Notas Importantes

- El frontend debe usar la misma **clave de sitio** que corresponda a la clave secreta configurada en el backend.
- Puedes modificar los orígenes permitidos en `CorsConfig.java` si tu frontend se encuentra en otra URL.

---

## 📄 Licencia

Este proyecto está bajo la [Licencia Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0).
