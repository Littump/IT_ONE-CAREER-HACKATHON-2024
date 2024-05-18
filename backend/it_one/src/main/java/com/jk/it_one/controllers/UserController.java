package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.exceptions.UserExistException;
import com.jk.it_one.models.User;
import com.jk.it_one.requestDtos.AuthorisationDto;
import com.jk.it_one.requestDtos.RegistrationDto;
import com.jk.it_one.requestDtos.UserPatchDto;
import com.jk.it_one.responceDtos.ProfileDto;
import com.jk.it_one.security.JWTCore;
import com.jk.it_one.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api")
@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTCore jwtCore;

    @Autowired
    public UserController(@Lazy UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTCore jwtCore) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtCore = jwtCore;
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody RegistrationDto registrationDto) {
        if (userService.existsByUsername(registrationDto.getUsername())) {
            throw new UserExistException(registrationDto.getUsername());
        }
        registrationDto.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return userService.save(new User(registrationDto));
    }

    @PostMapping("/auth/token/login")
    public String signIn(@Valid @RequestBody AuthorisationDto authorisationDto) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authorisationDto.getUsername(),
                        authorisationDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtCore.generateToken(authentication);
    }

    @PatchMapping("/users/me")
    public User updateUser(Principal principal, @Valid @RequestBody UserPatchDto fieldsForChange) {
        User currentMe = userService.findMe(principal);
        if (fieldsForChange.getPassword() != null) {
            fieldsForChange.setPassword(passwordEncoder.encode(fieldsForChange.getPassword()));
        }
        currentMe.patch(fieldsForChange);
        return userService.save(currentMe);
    }

    @GetMapping("/profile")
    public ProfileDto getProfile(Principal principal, @RequestParam Currency currency) {
        return userService.getUserProfile(principal, currency);
    }
}
