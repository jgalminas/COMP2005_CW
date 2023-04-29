# Build
FROM amazoncorretto:8-al2023-jdk AS BUILD
COPY . .
RUN ./gradlew build

# Package
FROM amazoncorretto:8-al2023-jre
VOLUME /tmp
COPY --from=BUILD build/libs/*.jar /user/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/user/local/lib/app.jar"]