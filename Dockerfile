##Definindo um argumento para a imagem base e usando a imagem definida no argumento.
#ARG BASE_IMAGE=openjdk:17-jdk-slim
#FROM $BASE_IMAGE
#
## Definindo o diretório de trabalho dentro do contêiner.
#ARG APP_DIR=/app
#ENV APP_DIR=${APP_DIR}
#WORKDIR ${APP_DIR}
#
##Copiando o arquivo JAR para dentro do contêiner.
#COPY target/*.jar ${APP_DIR}/seareport.jar
#
##Comando para iniciar o aplicativo Java dentro do contêiner,
##o ${JAVA_OPTS} é uma variável de ambiente definida no arquivo docker-compose.yml.
#CMD ["sh", "-c", "java ${JAVA_OPTS} -jar seareport.jar"]
#


#Versão do JDK
FROM openjdk:17-jdk-slim

# define o mantenedor da imagem
#LABEL maintainer="Benefrancis"
LABEL description="Aplicação API/RestFull para cadastramento de Pessoas e Empresas"

#copia o arquivo que termina com .jar
#renomeando para pessoa-service.jar
COPY target/*.jar seareport.jar

# Expoe a porta 80
EXPOSE 80

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "seareport.jar"]