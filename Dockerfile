FROM eclipse-temurin:17-jdk-alpine as prod

COPY target/SpringBootRESTDisco-0.0.1-SNAPSHOT.jar app.jar
ENV SERVER_PORT=8090
ENV DATABASE_URL=jdbc:oracle:thin:@localhost:1521:xe
ENV DATABASE_USERNAME=Oracle
ENV DATABASE_PASSWORD=123456
EXPOSE 8090
ENTRYPOINT ["java","-jar","app.jar"]

