package br.com.fiap.seareport.controller;

import br.com.fiap.seareport.dto.request.UserRequest;
import br.com.fiap.seareport.dto.request.UserRequestLogin;
import br.com.fiap.seareport.dto.response.ReportResponse;
import br.com.fiap.seareport.dto.response.UserResponse;
import br.com.fiap.seareport.entity.User;
import br.com.fiap.seareport.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAll(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size
    ) {
        var item = User.builder()
                .username(username)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("username", match -> match.contains());

        Example<User> example = Example.of(item, matcher);

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "username");

        var users = service.findAll(example).stream()
                .map(service::toResponse)
                .toList();
        Page<UserResponse> pagina = new PageImpl<>( users, pageable, users.size() );

        return ResponseEntity.ok( pagina );
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest r) {
        var saved = service.save(r);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> login(@RequestBody String auth) {
        User user = service.findByAuth(auth);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( user.getId() )
                .toUri();

        return ResponseEntity.ok(service.toResponse(user));
    }
}
