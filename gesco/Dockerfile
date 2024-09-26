# Etapa de build
FROM eclipse-temurin:22-jdk AS build

# Definir diretório de trabalho
WORKDIR /app

# Copiar o arquivo gradle e as dependências para o contêiner
COPY gradle /app/gradle
COPY gradlew /app/
COPY build.gradle /app/
COPY settings.gradle /app/

# Adicionar permissão de execução para o gradlew
RUN chmod +x ./gradlew

# Baixar as dependências do Gradle
RUN ./gradlew build --no-daemon || return 0

# Copiar o código-fonte
COPY src /app/src

# Compilar o aplicativo
RUN ./gradlew bootJar --no-daemon

# Etapa final
FROM eclipse-temurin:22-jdk

# Definir diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado para a imagem final
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
