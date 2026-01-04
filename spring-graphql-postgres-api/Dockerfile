#Build: Gradle + Jdk21
FROM gradle:8.5-jdk21 AS builder
WORKDIR /app
COPY . .
#Permisos
RUN chmod +x gradlew
#Generar Jar (ejecutable)
RUN ./gradlew clean build -x test

#Runtime: imagen con Jre (mas ligera)
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar java_app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/java_app.jar"]