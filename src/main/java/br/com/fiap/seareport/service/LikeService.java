package br.com.fiap.seareport.service;

import br.com.fiap.seareport.dto.ServiceDTO;
import br.com.fiap.seareport.dto.request.LikeRequest;
import br.com.fiap.seareport.dto.response.LikeResponse;
import br.com.fiap.seareport.entity.Like;
import br.com.fiap.seareport.entity.Post;
import br.com.fiap.seareport.entity.User;
import br.com.fiap.seareport.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LikeService implements ServiceDTO<Like, LikeRequest, LikeResponse, Long> {

    @Autowired private LikeRepository repo;
    @Autowired private UserService userService;
    @Autowired private PostService postService;

    @Override
    public Like toEntity(LikeRequest r) {
        if (Objects.isNull(r)) return null;
        var user = userService.findById(r.idUser());
        var post = postService.findById(r.idPost());
        return Like.builder()
                .user(user)
                .post(post)
                .build();
    }

    @Override
    public LikeResponse toResponse(Like e) {
        if (Objects.isNull(e) || (e.getPost() == null && e.getUser() ==null)) return null;
        var user = userService.findById(e.getUser().getId());
        var post = postService.findById(e.getPost().getId());
        if (Objects.isNull(user) || Objects.isNull(post)) return null;

        return LikeResponse.builder()
                .id(e.getId())
                .username(user.getUsername())
                .contentPost(post.getContentPost())
                .build();
    }

    @Override
    public List<Like> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Like> findAll(Example<Like> example) {
        return repo.findAll(example);
    }

    @Override
    public Like findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Like save(LikeRequest r) {
        return repo.save(toEntity(r));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Like findByIdUserAndIdPost(Long idUser, Long idPost) {
        User user = userService.findById(idUser);
        Post post = postService.findById(idPost);
        if (Objects.isNull(user) || Objects.isNull(post)) return null;
        return repo.findByUserAndPost(user, post);
    }


}
