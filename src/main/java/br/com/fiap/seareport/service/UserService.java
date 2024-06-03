package br.com.fiap.seareport.service;

import br.com.fiap.seareport.dto.ServiceDTO;
import br.com.fiap.seareport.dto.request.UserRequest;
import br.com.fiap.seareport.dto.response.UserResponse;
import br.com.fiap.seareport.entity.User;
import br.com.fiap.seareport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements ServiceDTO<User, UserRequest, UserResponse> {
    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User toEntity(UserRequest r) {
        if (Objects.isNull(r)) return null;
        return User.builder()
                .name(r.email())
                .password(passwordEncoder.encode(r.password()))
                .email(r.email())
                .phoneNumber(r.phoneNumber())
                .build();
    }

    @Override
    public UserResponse toResponse(User e) {
        if (Objects.isNull(e)) return null;
        return UserResponse.builder()
                .id(e.getId())
                .name(e.getName())
                .username(e.getUsername())
                .build();
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public List<User> findAll(Example<User> example) {
        return repo.findAll(example);
    }

    @Override
    public User findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public User save(UserRequest r) {
//        var user = toEntity(r);
//        user.setPassword(passwordEncoder.encode(r.password()));
        return repo.save(toEntity(r));
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
