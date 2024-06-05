package br.com.fiap.seareport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeareportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeareportApplication.class, args);
	}

}

//TODO: Deploy em nuvem
//TODO: HATEOAS
//TODO: Links dos Deploys em nuvem, com instruções para acessos e testes (usuário, senha, etc)
//TODO: Link do Vídeo demonstrando a software funcionando
//TODO: Link do Vídeo Pich com duração máxima de 3 minutos.
//TODO: comando para build apartir do compose "docker-compose up -d --build"
