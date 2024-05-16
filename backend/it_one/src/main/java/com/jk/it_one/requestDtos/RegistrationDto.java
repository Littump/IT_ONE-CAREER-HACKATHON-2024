package com.jk.it_one.requestDtos;

import com.jk.it_one.enums.Currency;
import lombok.Data;

@Data
public class RegistrationDto {
    private String username;
    private String name;
    private String password;
    private Currency currency;
}
