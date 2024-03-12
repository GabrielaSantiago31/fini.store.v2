package io.github.gabriela.santiago31.fini.store.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import io.github.gabriela.santiago31.fini.store.v2.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	boolean existsByCpf(String cpf);
	
	UserDetails findByLogin(String login);
	
}
