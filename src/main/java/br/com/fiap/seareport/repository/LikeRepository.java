package br.com.fiap.seareport.repository;

import br.com.fiap.seareport.entity.Like;
import br.com.fiap.seareport.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List< Like > findByUser(User user);

}
