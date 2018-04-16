package cz.fi.muni.TACOS.persistence.entity;


import cz.fi.muni.TACOS.persistence.enums.UserRole;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vyskocil Pavel <vyskocilpavel@muni.cz>
 */
@Entity
public class User implements Serializable {

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

    @Email
    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated
    @NotNull
    @Column(nullable = false)
    private UserRole role;

    @NotNull
    @Column(nullable = false)
    private String passwordHash;

    @NotNull
    @Column(nullable = false)
    @OneToMany(mappedBy = "submitter", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    public User() {
    }

    public User(String name, String surname, String email, UserRole role, String passwordHash) {
        checkString(name, "Name");
        checkString(surname, "Surname");
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.passwordHash = passwordHash;
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
        checkString(name, "Name");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        checkString(surname, "Surname");
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

    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

    public void addSubmittedOrder(Order order) {
        this.orders.add(order);
        order.setSubmitterFromOneSide(this);
    }

    public void removeSubmittedOrder(Order order) {
        this.orders.remove(order);
        order.setSubmitterFromOneSide(null);
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        checkString(passwordHash, "passwordHash");
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;

        return getName().equals(user.getName()) &&
                getSurname().equals(user.getSurname()) &&
                getEmail().equals(user.getEmail()) &&
                getRole().equals(user.getRole()) &&
                getOrders().equals(user.getOrders());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getEmail(), getRole(), getOrders());
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

    private void checkString(String value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(name +" cannot be null.");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException(name + " cannot be empty.");
        }
    }

}
