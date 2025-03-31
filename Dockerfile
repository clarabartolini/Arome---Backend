# Etapa de build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copia apenas o pom.xml primeiro e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte restante
COPY src ./src

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests

# Etapa final
FROM openjdk:17-jdk-slim
WORKDIR /app

# Garante que o arquivo JAR está realmente presente
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Define o comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
