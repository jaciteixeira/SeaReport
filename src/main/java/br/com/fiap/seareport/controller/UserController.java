package br.com.fiap.seareport.controller;

import br.com.fiap.seareport.dto.request.UserRequest;
import br.com.fiap.seareport.dto.request.UserRequestLogin;
import br.com.fiap.seareport.dto.response.UserResponse;
import br.com.fiap.seareport.entity.User;
import br.com.fiap.seareport.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(
            @RequestParam(name = "username", required = false) String username
    ) {
        var item = User.builder()
                .username(username)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("username", match -> match.contains());

        Example<User> example = Example.of(item, matcher);

        List<User> users = service.findAll(example);

        return ResponseEntity.ok(users.stream().map(service::toResponse).toList());
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest r) {
        var saved = service.save(r);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequestLogin r) {
        var user = service.findByUsername(r.username());if (user != null && passwordEncoder.matches(r.password(), user.getPassword())) {
            return ResponseEntity.ok(service.toResponse(user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
