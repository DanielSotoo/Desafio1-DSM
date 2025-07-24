# Desafio1-DSM

**Primer Desafío Práctico - Desarrollo de Software para Móviles DSM441 G02L**

## Información del Estudiante

- **Nombre:** Oscar Daniel Soto Jovel
- **Carnet:** SJ241841
- **Materia:** Desarrollo de Software para Móviles (DSM441)
- **Grupo:** G02L
  
## Tecnologías Utilizadas

- **Lenguaje:** Kotlin
- **IDE:** Android Studio 2025.1.1
- **SDK Mínimo:** API 24 (Android 7.0)
- **Arquitectura:** Actividad única con vistas tradicionales
- **UI:** XML Layouts con Material Design

## Instalación y Configuración

### 1. Clonar o Descargar el Proyecto
```bash
git clone https://github.com/DanielSotoo/Desafio1-DSM.git
cd Desafio1-DSM
```

### 2. Abrir en Android Studio
1. Abre Android Studio
2. Selecciona "Open an existing project"
3. Navega hasta la carpeta del proyecto
4. Selecciona la carpeta raíz del proyecto

### 3. Sincronizar Dependencias
- Android Studio automáticamente sincronizará las dependencias
- Si no lo hace automáticamente, haz clic en "Sync Now"

## Cómo Ejecutar la Aplicación

### Opción 1: Emulador Android
1. En Android Studio, haz clic en "AVD Manager"
2. Crea un nuevo dispositivo virtual o selecciona uno existente
3. Haz clic en el botón "Run" o presiona `Shift + F10`
4. Selecciona el emulador creado

### Opción 2: Dispositivo Físico
1. Habilita "Opciones de desarrollador" en tu dispositivo:
   - Ve a Configuración > Acerca del teléfono
   - Toca 7 veces en "Número de compilación"
2. Habilita "Depuración USB" en Opciones de desarrollador
3. Conecta tu dispositivo via USB
4. Haz clic en "Run" y selecciona tu dispositivo

## Guía de Uso

### Paso a Paso:
1. **Ingresa el monto total** de la cuenta en el primer campo
2. **Especifica el número de personas** que dividirán la cuenta
3. **Selecciona el porcentaje de propina** deseado (10%, 15%, 20% o Otro)
4. **Opcional:** Activa el switch "Incluir IVA (16%)" si es necesario
5. **Presiona "Calcular"** para obtener los resultados
6. **Presiona "Limpiar"** para borrar todos los campos

### Ejemplo de Uso:
- Monto total: $100.00
- Personas: 4
- Propina: 15%
- IVA: Activado

**Resultado:**
```
Propina: $ 17.40
Total a pagar: $ 133.40
Por persona: $ 33.35
```

## Estructura del Proyecto

```
app/
├── src/main/
│   ├── java/com/example/desafiopractico1/
│   │   └── MainActivity.kt
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml
│   │   ├── drawable/
│   │   │   ├── edittext_background.xml
│   │   │   └── button_background.xml
│   │   └── values/
│   │       ├── colors.xml
│   │       ├── strings.xml
│   │       └── styles.xml
│   └── AndroidManifest.xml
├── build.gradle
└── README.md
```

**Desarrollado por:** Oscar Daniel Soto Jovel  
**Fecha:** Julio 2025  
**Versión:** 1.0.0
