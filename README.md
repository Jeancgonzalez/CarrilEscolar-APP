# CarrilEscolar-APP 🚸📱

## Descripción general

CarrilEscolar-APP es una aplicación móvil nativa para Android desarrollada en Java que permite el registro y autenticación de usuarios mediante Firebase Firestore. El proyecto incluye funcionalidades básicas de gestión de usuarios, como creación de cuentas, inicio de sesión y navegación entre diferentes pantallas de la aplicación.

La aplicación fue desarrollada como un proyecto académico con el objetivo de aplicar conceptos de desarrollo móvil, integración con bases de datos en la nube y gestión de interfaces de usuario en Android.

---

## Funcionalidades principales

### Registro de usuarios
- Registro de nuevos usuarios mediante formulario.
- Almacenamiento de información en Firebase Firestore.
- Gestión de datos como nombre, cédula, celular, correo electrónico y contraseña.

### Inicio de sesión
- Validación de credenciales almacenadas en Firestore.
- Verificación de usuario y contraseña para acceder al sistema.

### Navegación entre pantallas
- Uso de Intents para la transición entre actividades.
- Envío de información entre pantallas cuando es necesario.

### Menú principal
- Acceso centralizado a las funcionalidades disponibles en la aplicación.
- Interfaz sencilla y fácil de utilizar.

### Integración con Firebase
- Conexión con Firebase Firestore para almacenamiento de datos.
- Gestión de información en tiempo real desde la nube.

---

## Tecnologías utilizadas

- **Java**
- **Android SDK**
- **AndroidX**
- **Firebase Firestore**
- **Gradle**
- **Android Studio**

---

## Estructura del proyecto

```text
app/
├── src/
│   └── main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/conexionhttp/
│       │   ├── MainActivity.java
│       │   ├── login.java
│       │   ├── menu.java
│       │   └── hijos.java
│       └── res/
├── google-services.json
└── build.gradle.kts
```

---

## Instalación y ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/Jeancgonzalez/CarrilEscolar-APP.git
cd CarrilEscolar-APP
```

2. Abrir el proyecto en Android Studio.

3. Sincronizar las dependencias de Gradle.

4. Verificar la configuración de Firebase:
   - Si utilizarás tu propio proyecto Firebase, reemplaza el archivo `google-services.json`.
   - Habilita Firestore desde la consola de Firebase.

5. Ejecutar la aplicación en un emulador o dispositivo Android físico.

---

## Requisitos

- Android Studio
- Android SDK compatible
- JDK 11 o superior
- Conexión a Internet
- Cuenta de Firebase (opcional para configuración propia)

---

## Posibles mejoras

- Implementar Firebase Authentication para una gestión más segura de usuarios.
- Cifrar o hashear contraseñas antes de almacenarlas.
- Añadir validaciones avanzadas de formularios.
- Implementar reglas de seguridad en Firestore.
- Mejorar la experiencia de usuario y el manejo de errores.

---

## Autor

**Jean Carlos González**

Repositorio: https://github.com/Jeancgonzalez/CarrilEscolar-APP.git
Proyecto académico desarrollado para el aprendizaje de desarrollo móvil Android e integración con servicios en la nube.

**Repositorio:** https://github.com/Jeancgonzalez/CarrilEscolar-APP
