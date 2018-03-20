package cz.fi.muni.TACOS.persistence.entity;


import cz.fi.muni.TACOS.persistence.enums.UserRole;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Vyskocil Pavel <vyskocilpavel@muni.cz>
 */
@Entity
public class User implements Serializable{

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String surname;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String email;

    @Enumerated
    @NotNull
    @Column(nullable = false)
    private UserRole role;

    public User() {
    }

    public User(String name, String surname, String email, UserRole role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

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
        if (o == null || !(o instanceof User)) return false;
        User user = (User) o;

        return getName().equals(user.getName()) &&
                getSurname().equals(user.getSurname()) &&
                getEmail().equals(user.getEmail());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getSurname(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
