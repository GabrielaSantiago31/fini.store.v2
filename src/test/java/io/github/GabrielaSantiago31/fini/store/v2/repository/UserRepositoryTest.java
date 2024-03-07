package io.github.GabrielaSantiago31.fini.store.v2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import io.github.GabrielaSantiago31.fini.store.v2.enumeration.UserRole;
import io.github.GabrielaSantiago31.fini.store.v2.models.Address;
import io.github.GabrielaSantiago31.fini.store.v2.models.User;


@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	Address address;
	User user;
	User user2;
	
	@BeforeEach
	void setUp() {
		address = new Address(1L, "Rua P",32,"Fundos","Campo Grande","Rio de Janeiro","RJ","Brasil","69042670");
		user = new User(1L,"Gabriela", "Santiago", "gabriela.santiago@gmail.com", "21968236525","","12976045712","290319",address,UserRole.ADMIN,"gabriela.santiago@gmail.com","12345678", null);
		user2 = new User(2L,"Flavio", "Santiago", "flavio.santiago@gmail.com", "21968246425","","21933039086","101189",address,UserRole.USER,"flavio.santiago@gmail.com","89101112", null);
	}
	
	@DisplayName("Save a book")
	@Test
	void testGivenAUserObject_WhenSave_ShouldReturnASavedUser() {
		
		User savedUser = userRepository.save(user);
		
		Assertions.assertNotNull(savedUser);
		Assertions.assertEquals(1L, savedUser.getId());
	}
	
	@DisplayName("Find All Books")
	@Test
	void testGivenAListOfBooks_WhenFindAll_ShouldReturnAListOfSavedBooks() {
		
		userRepository.save(user);
		userRepository.save(user2);
		
		List<User> users = userRepository.findAll();
		
		Assertions.assertNotNull(users);
		Assertions.assertEquals(2, users.size());
	}

}
