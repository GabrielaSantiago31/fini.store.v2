package io.github.GabrielaSantiago31.fini.store.v2.models;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.GabrielaSantiago31.fini.store.v2.enumeration.UserRole;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="tb_user")
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	private String phone1;
	
	private String phone2;
	
	@Column(unique = true)
	private String cpf;
	
	private String dateOfBirth;
	
	@NonNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id", referencedColumnName = "id")
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@Column(unique = true)
	private String login;
	
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	
	public User(String login, String password, UserRole role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}
	

	@Override
	public List<SimpleGrantedAuthority> getAuthorities() {
		if (this.role == UserRole.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		}
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
