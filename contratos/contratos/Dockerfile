# ----- Estágio 1: Build (Compilação) -----
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /build-app

# Copia o arquivo pom.xml e a pasta src juntos para garantir que tudo será considerado nas dependências
COPY pom.xml .
COPY src ./src

# Compila o projeto e empacota a aplicação em um arquivo JAR, pulando os testes
RUN mvn clean package -DskipTests

# ----- Estágio 2: Runtime (Execução) -----
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia apenas o JAR gerado no estágio de build
COPY --from=builder /build-app/target/*.jar app.jar

# Cria usuário e grupo dedicados (não-root)
RUN addgroup --system springappgroup && adduser --system --ingroup springappgroup springappuser
USER springappuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
