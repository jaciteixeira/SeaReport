package br.com.fiap.seareport.dto;

import org.springframework.data.domain.Example;

import java.util.List;

public interface ServiceDTO <Entity, Request, Response, ID>{

    Entity toEntity(Request r);

    Response toResponse(Entity e);

    List<Entity> findAll();

    List<Entity> findAll(Example<Entity> example);

    Entity findById (ID id);

    Entity save(Request r);
}
