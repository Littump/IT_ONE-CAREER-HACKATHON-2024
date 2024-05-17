package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthorisationDto {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
