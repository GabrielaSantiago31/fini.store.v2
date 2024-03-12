package io.github.gabriela.santiago31.fini.store.v2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;

import io.github.gabriela.santiago31.fini.store.v2.enumeration.UserRole;
import io.github.gabriela.santiago31.fini.store.v2.models.Address;
import io.github.gabriela.santiago31.fini.store.v2.models.User;
import io.github.gabriela.santiago31.fini.store.v2.repository.UserRepository;

@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	Address address;
	User user;
	
	@BeforeEach
	void setUp() {
		address = new Address(1L, "Rua P",32,"Fundos","Campo Grande","Rio de Janeiro","RJ","Brasil","69042670");
		user = new User(1L,"Gabriela", "Santiago", "gabriela.santiago@gmail.com", "21968236925","","40628709080","290319",address,UserRole.ADMIN,"gabriela.santiago@gmail.com","12345678", null);
	}
	
	@DisplayName("Save a user")
	@Test
	void testGivenAUserObject_WhenSave_ShouldReturnASavedUser() {
		
		User savedUser = userRepository.save(user);
		
		Assertions.assertNotNull(savedUser);
		Assertions.assertEquals(user.getId(), savedUser.getId());
	}
	
	@DisplayName("Find all users")
	@Test
	void testGivenAListOfUsers_WhenFindAll_ShouldReturnAListOfSavedUsers() {
		
		User user2 = new User(2L,"Flavio", "Santiago", "flavio.santiago@gmail.com", "21968246425","","21933039086","101189",address,UserRole.USER,"flavio.santiago@gmail.com","89101112", null);
		
		userRepository.save(user);
		userRepository.save(user2);
		
		List<User> users = userRepository.findAll();
		
		Assertions.assertNotNull(users);
		Assertions.assertEquals(2, users.size());
	}
	
	@DisplayName("Find a user by id")
	@Test
	void testGivenAUserId_WhenFindById_ShouldReturnAUserRelatedToTheId() {
		
		userRepository.save(user);
		
		User savedUser = userRepository.findById(user.getId()).get();
		
		Assertions.assertNotNull(savedUser);
		Assertions.assertEquals(1L, savedUser.getId());
	}
	
	@DisplayName("Exist by cpf")
	@Test
	void testGivenAUserCpf_WhenExistsByCpf_ShouldReturnTrue() {
		
		userRepository.save(user);
		
		boolean savedUser = userRepository.existsByCpf(user.getCpf());
		
		Assertions.assertTrue(savedUser);
	}
	
	@DisplayName("Find by login")
	@Test
	void testGivenAUserLogin_WhenFindByLogin_ShouldReturnAUserDetail() {
		
		userRepository.save(user);
		
		UserDetails savedUser = userRepository.findByLogin(user.getLogin());
		
		Assertions.assertNotNull(savedUser);
		Assertions.assertEquals(user.getLogin(), savedUser.getUsername());
		Assertions.assertEquals(user.getPassword(), savedUser.getPassword());
	}
	
	@DisplayName("Update a user's attributes")
	@Test
	void testGivenAUser_WhenUpdate_ShouldModifyUserAttributes() {
		
		userRepository.save(user);
		
		User savedUser = userRepository.findById(user.getId()).get();
		
		savedUser.setFirstName("Vitória");
		savedUser.setLastName("Machado");
		
		userRepository.save(savedUser);
		
		Assertions.assertNotNull(savedUser);
		Assertions.assertNotEquals(savedUser.getFirstName(), user.getFirstName());
		Assertions.assertNotEquals(savedUser.getLastName(), user.getLastName());
		Assertions.assertEquals("Vitória", savedUser.getFirstName());
		Assertions.assertEquals("Machado", savedUser.getLastName());
	}
	
	@DisplayName("Delete a user by id")
	@Test
	void testGivenAUserId_WhenDeleteById_ShouldRemoveASavedUser() {
		
		userRepository.save(user);
		
		userRepository.deleteById(user.getId());
		
		boolean isUserSaved = userRepository.existsById(user.getId());
		
		Assertions.assertFalse(isUserSaved);
	}

}
