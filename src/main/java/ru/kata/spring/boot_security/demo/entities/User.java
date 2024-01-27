package ru.kata.spring.boot_security.demo.entities;

import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
//Все основные геттеры и сеттеры от ломбок, реализация Equals, hashcode и.т.д...
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "username", unique = true)
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100,
            message = "Имя должно быть от 2 до 100 символов длиной")
    private String username;
    private String password;
    @Email private String email;
    private String firstName;
    private String lastName;
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
    // getPassword и getUsername реализованы в ломбок

    public User(String username, String password, String email,
                String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {}
}
