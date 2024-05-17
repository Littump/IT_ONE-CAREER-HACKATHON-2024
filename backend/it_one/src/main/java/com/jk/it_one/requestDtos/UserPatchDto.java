package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserPatchDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;
}
