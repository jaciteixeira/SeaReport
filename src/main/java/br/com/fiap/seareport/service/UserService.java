package br.com.fiap.seareport.service;

import br.com.fiap.seareport.dto.ServiceDTO;
import br.com.fiap.seareport.dto.request.UserRequest;
import br.com.fiap.seareport.dto.request.UserRequestLogin;
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
public class UserService implements ServiceDTO<User, UserRequest, UserResponse, Long> {
    @Autowired
    private UserRepository repo;
    @Autowired
    private AuthService authService;

    @Override
    public User toEntity(UserRequest r) {
        if (Objects.isNull(r)) return null;
        return User.builder()
                .username(r.username())
                .phoneNumber(r.phoneNumber())
                .auth(authService.toEntity(r.auth()))
                .build();
    }

    @Override
    public UserResponse toResponse(User e) {
        if (Objects.isNull(e)) return null;
        return UserResponse.builder()
                .id(e.getId())
                .xp(e.getXp())
                .username(e.getUsername())
                .auth(authService.toResponse(e.getAuth()))
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
        var user = toEntity(r);
        user.setXp(0);
//        user.setPassword(passwordEncoder.encode(r.password()));
        return repo.save(user);
    }

    public User save(User user){
        return repo.save(user);
    }

    public User findByUsername(UserRequestLogin r) {
        var user = repo.findByUsername(r.username());
        if (user != null ){
            return user;
        }
        return null;
    }

    public User findByAuth(String authId) {
        var auth = authService.findById(authId);
        System.out.println(auth);
        return repo.findByAuth(auth);
    }
}
