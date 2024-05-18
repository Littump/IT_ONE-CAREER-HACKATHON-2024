package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RegistrationDto {
    @NotBlank(message = "'username' shouldn't be empty")
    @JsonProperty("username")
    private String username;

    @NotBlank(message = "'name' shouldn't be empty")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "'password' shouldn't be empty")
    @JsonProperty("password")
    private String password;
}
