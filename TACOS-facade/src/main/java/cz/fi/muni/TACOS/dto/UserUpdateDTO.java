package cz.fi.muni.TACOS.dto;

import cz.fi.muni.TACOS.persistence.enums.UserRole;

import java.util.Objects;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class UserUpdateDTO {

	private Long id;

	private String name;

	private String surname;

	private String email;

	private UserRole role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDTO userDTO = (UserDTO) o;
		return Objects.equals(getName(), userDTO.getName()) &&
				Objects.equals(getSurname(), userDTO.getSurname()) &&
				Objects.equals(getEmail(), userDTO.getEmail()) &&
				getRole() == userDTO.getRole();
	}

	@Override
	public int hashCode() {

		return Objects.hash(getName(), getSurname(), getEmail(), getRole());
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", email='" + email + '\'' +
				", role=" + role +
				'}';
	}
}
