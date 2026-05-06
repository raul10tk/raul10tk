# Gastos

App Android nativa, minimalista, para registrar tus gastos diarios.

- **Stack:** Kotlin · Jetpack Compose · Material 3 · Room
- **minSdk:** 26 (Android 8.0+)
- **Persistencia:** local (Room/SQLite) — todo se guarda en tu móvil.

## Funcionalidades

- Pantalla principal con el total gastado **hoy** y el total acumulado.
- Añadir un gasto con cantidad, categoría (Comida, Transporte, Ocio, Hogar, Salud, Compras, Otros) y nota opcional.
- Eliminar gastos con un toque.
- Tema claro/oscuro automático y colores dinámicos en Android 12+.

## Instalar la APK en tu móvil

Cada push a esta rama genera una APK lista para sideload. Hay dos formas de obtenerla:

### Opción A — Release (recomendada, descarga directa desde el móvil)

1. Abre la pestaña [Releases](../../releases) del repositorio en tu móvil.
2. Descarga el archivo `Gastos-<sha>.apk` del release más reciente.
3. Android te pedirá permiso para instalar desde "orígenes desconocidos" — actívalo solo para tu navegador.
4. Abre la APK descargada y pulsa **Instalar**.

### Opción B — Artifact del workflow

1. Ve a [Actions](../../actions) → último run del workflow `Build APK`.
2. En la sección *Artifacts* descarga `gastos-apk`.
3. Descomprime el ZIP y transfiere la APK al móvil (USB, Drive, etc.).
4. Instálala como en la opción A.

> La APK está firmada con la clave de **debug** de Android. Es perfectamente válida para instalación personal, pero no se puede subir a Google Play.

## Compilar localmente

```bash
gradle wrapper --gradle-version 8.7
./gradlew assembleDebug
# La APK queda en app/build/outputs/apk/debug/app-debug.apk
```

Necesitas JDK 17 y el Android SDK (API 34) instalados.
