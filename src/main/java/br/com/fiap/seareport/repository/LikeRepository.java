package br.com.fiap.seareport.repository;

import br.com.fiap.seareport.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
