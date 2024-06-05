package br.com.fiap.seareport.service;

import br.com.fiap.seareport.dto.ServiceDTO;
import br.com.fiap.seareport.dto.request.AuthRequest;
import br.com.fiap.seareport.dto.response.AuthResponse;
import br.com.fiap.seareport.entity.Auth;
import br.com.fiap.seareport.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthService implements ServiceDTO<Auth, AuthRequest, AuthResponse, String> {

    @Autowired
    private AuthRepository repo;

    @Override
    public Auth toEntity(AuthRequest r) {
        if (Objects.isNull(r)) return null;
        return Auth.builder()
                .id(r.id())
                .email(r.email())
                .build();
    }

    @Override
    public AuthResponse toResponse(Auth e) {
        if (Objects.isNull(e)) return null;
        return AuthResponse.builder()
                .id(e.getId())
                .email(e.getEmail())
                .build();
    }

    @Override
    public List<Auth> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Auth> findAll(Example<Auth> example) {
        return repo.findAll(example);
    }

    @Override
    public Auth findById(String id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Auth save(AuthRequest r) {
        return repo.save(toEntity(r));
    }
}
