FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw -q -e -DskipTests dependency:go-offline

CMD ["./mvnw", "spring-boot:run"]