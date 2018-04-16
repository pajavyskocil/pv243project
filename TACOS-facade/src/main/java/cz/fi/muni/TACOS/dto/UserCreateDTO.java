package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.persistence.enums.UserRole;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserCreateDTO {

	private String name;

	private String surname;

	private String email;

	private UserRole role;

	private String password;

	private Set<OrderDTO> orders = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Set<OrderDTO> getOrders() {
		return Collections.unmodifiableSet(orders);
	}

	public void setOrders(Set<OrderDTO> orders) {
		this.orders = orders;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDTO userDTO = (UserDTO) o;
		return Objects.equals(getName(), userDTO.getName()) &&
				Objects.equals(getSurname(), userDTO.getSurname()) &&
				Objects.equals(getEmail(), userDTO.getEmail()) &&
				Objects.equals(getRole(), userDTO.getRole()) &&
				Objects.equals(getOrders(), userDTO.getOrders());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getName(), getSurname(), getEmail(), getRole(), getOrders());
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", email='" + email + '\'' +
				", role=" + role +
				'}';
	}
}
