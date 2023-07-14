package com.store.fini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.fini.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
}
