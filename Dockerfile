FROM ubuntu:latest AS build

# Definindo argumento de build para a versão do JDK
ARG JDK_VERSION=17

# Usando o argumento para instalar a versão específica do JDK
RUN apt-get update && apt-get install openjdk-${JDK_VERSION}-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install


FROM openjdk:17-jdk-slim

# Definindo uma variável de ambiente para a descrição
ENV APP_DESCRIPTION="Uma plataforma de denúncia de crimes ocorridos em alto-mar e áreas litorâneas com gamificação."
LABEL description=${APP_DESCRIPTION}

EXPOSE 80

COPY --from=build /target/*.jar seareport.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "seareport.jar"]
