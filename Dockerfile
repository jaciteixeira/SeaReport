
#FROM ubuntu:latest AS build
#
#RUN apt-get update
#RUN apt-get install openjdk-17-jdk -y
#COPY . .
#
#RUN apt-get install maven -y
#RUN mvn clean install
#
#FROM openjdk:17-jdk-slim
#
#LABEL description="Uma plataforma de denúncia de crimes ocorridos em alto-mar e áreas litorâneas com gamificação."
#
#EXPOSE 80
#
#COPY --from=build /target/*.jar seareport.jar
#
## Comando para rodar a aplicação
#ENTRYPOINT ["java", "-jar", "seareport.jar"]

# Use uma imagem base oficial do Maven e Java 17
FROM ringcentral/maven:3.8.2-jdk17 as build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Use uma imagem base oficial do JDK 17 para rodar a aplicação
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/seareport-0.0.1-SNAPSHOT.jar /usr/local/lib/seareport.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/usr/local/lib/seareport.jar"]
