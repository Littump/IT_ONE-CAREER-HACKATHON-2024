package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.requestDtos.RegistrationDto;
import com.jk.it_one.requestDtos.UserPatchDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
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

    @JsonIgnore
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(RegistrationDto registrationRequest) {
        this.username = registrationRequest.getUsername();
        this.name = registrationRequest.getName();
        this.password = registrationRequest.getPassword();
    }

    public void patch(UserPatchDto userPatchDto) {
        this.name = Objects.requireNonNullElse(userPatchDto.getName(), this.name);
        this.password = Objects.requireNonNullElse(userPatchDto.getPassword(), this.password);
    }
}