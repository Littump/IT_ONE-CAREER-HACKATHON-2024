package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.request_dtos.RegistrationDto;
import com.jk.it_one.request_dtos.UserPatchDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

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