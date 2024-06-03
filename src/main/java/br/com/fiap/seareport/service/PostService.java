package br.com.fiap.seareport.service;

import br.com.fiap.seareport.dto.ServiceDTO;
import br.com.fiap.seareport.dto.request.PostRequest;
import br.com.fiap.seareport.dto.response.PostResponse;
import br.com.fiap.seareport.entity.Post;
import br.com.fiap.seareport.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService implements ServiceDTO<Post, PostRequest, PostResponse> {

    @Autowired
    private PostRepository repo;

    @Override
    public Post toEntity(PostRequest r) {
        return Post.builder()
                .content(r.content())
                .date(LocalDateTime.now())
                .build();
    }

    @Override
    public PostResponse toResponse(Post e) {
        return PostResponse.builder()
                .id(e.getId())
                .content(e.getContent())
                .date(e.getDate())
                .build();
    }

    @Override
    public List<Post> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Post> findAll(Example<Post> example) {
        return repo.findAll(example);
    }

    @Override
    public Post findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Post save(PostRequest r) {
        return repo.save(toEntity(r));
    }
}
