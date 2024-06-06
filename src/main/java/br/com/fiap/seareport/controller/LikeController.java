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

    @GetMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<LikeResponse> findByIdUserAndIdPost(
            @PathVariable Long userId,
            @PathVariable Long postId
    ) {

        var like = service.findByIdUserAndIdPost(userId, postId);
        if (Objects.isNull( like)) return ResponseEntity.notFound().build();

        return ResponseEntity.ok( service.toResponse(like) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLike(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().body("Deleted");
    }

}
