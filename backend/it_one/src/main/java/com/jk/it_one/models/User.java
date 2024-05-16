package com.jk.it_one.models;

import com.jk.it_one.requestDtos.RegistrationDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(RegistrationDto registrationRequest) {
        this.username = registrationRequest.getUsername();
        this.name = registrationRequest.getName();
        this.password = registrationRequest.getPassword();
    }
}