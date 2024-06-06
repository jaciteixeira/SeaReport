
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

LABEL description="Uma plataforma de denúncia de crimes ocorridos em alto-mar e áreas litorâneas com gamificação."

EXPOSE 80

COPY --from=build /target/*.jar seareport.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "seareport.jar"]