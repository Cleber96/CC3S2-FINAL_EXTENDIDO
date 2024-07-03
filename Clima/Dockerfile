# Usar la imagen oficial de OpenJDK 17
FROM openjdk:17

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar solo los archivos necesarios para descargar dependencias
# Esto evita la invalidación de la caché en cada cambio de código
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Dar permiso de ejecución al script de Gradle
RUN chmod +x ./gradlew

# Instalar todas las dependencias de Gradle
RUN ./gradlew dependencies

# Ahora copiamos el resto del proyecto
COPY . .

# Compilar el proyecto usando Gradle (esto también resolverá las dependencias)
RUN ./gradlew build
RUN javac src/main/java/org/example/*.java
# Comando por defecto para ejecutar cuando el contenedor es iniciado
# Usar la forma de array de CMD asegura que se ejecute correctamente
CMD ["./gradlew", "test"]
CMD ["java", "-cp", "src/main/java", "org.example.Clima"]
