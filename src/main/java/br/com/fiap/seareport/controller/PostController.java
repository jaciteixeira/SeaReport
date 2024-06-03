package br.com.fiap.seareport.controller;

import br.com.fiap.seareport.dto.request.PostRequest;
import br.com.fiap.seareport.dto.response.PostResponse;
import br.com.fiap.seareport.entity.Post;
import br.com.fiap.seareport.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@RestController
@RequestMapping("/post")
public class PostController {

   @Autowired
   private PostService service;

   @Transactional
    @PostMapping
    public ResponseEntity<PostResponse> save(@RequestBody PostRequest r) {
        var saved = service.save(r);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<Page<PostResponse>> findAll(
            @RequestParam(name = "palavra-chave", required = false) String palavra,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size
    ) {
        var post = Post.builder()
                .content(palavra)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("content", match -> match.contains());

        Example<Post> example = Example.of(post, matcher);

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nomeFantasia" );

        var entity = service.findAll( example );

        if (Objects.isNull( entity ) || entity.isEmpty()) return ResponseEntity.notFound().build();

        var response = entity.stream().map( service::toResponse ).toList();

        Page<PostResponse> pagina = new PageImpl<>( response, pageable, response.size() );

        return ResponseEntity.ok( pagina );
   }
}
