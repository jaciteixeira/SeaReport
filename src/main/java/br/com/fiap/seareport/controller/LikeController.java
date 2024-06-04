package br.com.fiap.seareport.controller;

import br.com.fiap.seareport.dto.request.LikeRequest;
import br.com.fiap.seareport.dto.response.LikeResponse;
import br.com.fiap.seareport.dto.response.PostResponse;
import br.com.fiap.seareport.entity.Like;
import br.com.fiap.seareport.service.LikeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService service;

    @Transactional
    @PostMapping
    public ResponseEntity<LikeResponse> save(@RequestBody @Valid LikeRequest r) {
        var saved = service.save(r);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<LikeResponse>> findByUserId(
            @PathVariable Long userId,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "word" );

        var likes = service.findByIdUser(userId).stream().map( service::toResponse )
                .toList();
        if (Objects.isNull( likes ) || likes.isEmpty()) return ResponseEntity.notFound().build();

        Page<LikeResponse> pagina = new PageImpl<>( likes, pageable, likes.size() );

        return ResponseEntity.ok( pagina );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLike(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().body("Deleted");
    }

}
