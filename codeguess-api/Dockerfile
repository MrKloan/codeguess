FROM openjdk:11.0.11-jdk-slim AS builder
WORKDIR /src
COPY . .
RUN chmod +x ./mvnw && ./mvnw clean install

FROM openjdk:11.0.11-jre-slim
WORKDIR /opt/codeguess
COPY --from=builder /src/codeguess-api/target/codeguess-api-*.jar codeguess-api.jar
CMD ["java", "-jar", "codeguess-api.jar"]