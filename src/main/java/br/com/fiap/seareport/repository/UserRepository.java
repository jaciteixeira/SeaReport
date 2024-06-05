package br.com.fiap.seareport.repository;

import br.com.fiap.seareport.entity.Auth;
import br.com.fiap.seareport.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByAuth(Auth auth);
}
