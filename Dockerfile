# Build
FROM amazoncorretto:8-al2023-jdk AS BUILD
COPY . .
RUN ./gradlew build

# Package
FROM amazoncorretto:8-al2023-jre
VOLUME /tmp
COPY --from=BUILD build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]