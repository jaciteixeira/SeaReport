package br.com.fiap.seareport.repository;

import br.com.fiap.seareport.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, String> {
}
