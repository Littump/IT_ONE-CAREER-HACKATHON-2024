package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.exceptions.UserExistException;
import com.jk.it_one.models.User;
import com.jk.it_one.requestDtos.AuthorisationDto;
import com.jk.it_one.requestDtos.RegistrationDto;
import com.jk.it_one.requestDtos.UserPatchDto;
import com.jk.it_one.security.JWTCore;
import com.jk.it_one.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTCore jwtCore;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTCore jwtCore) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtCore = jwtCore;
    }

    @PostMapping("/users")
    User createUser(@RequestBody RegistrationDto registrationDto) {
        if (userService.existsByUsername(registrationDto.getUsername())) {
            throw new UserExistException(registrationDto.getUsername());
        }
        registrationDto.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return userService.save(new User(registrationDto));
    }

    @PostMapping("/auth/token/login")
    String signIn(@RequestBody AuthorisationDto authorisationDto) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authorisationDto.getUsername(),
                        authorisationDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtCore.generateToken(authentication);
    }

    @GetMapping("/profile")
    ResponseEntity<?> getProfile(Principal principal, @RequestParam Currency currency) {
        return ResponseEntity.ok(userService.getUserProfile(principal, currency)); //TODO
    }

    @PatchMapping("/users/me")
    User updateUser(Principal principal, @RequestBody UserPatchDto fieldsForChange) {
        User currentMe = userService.findMe(principal);
        if (fieldsForChange.getPassword() != null) {
            fieldsForChange.setPassword(passwordEncoder.encode(fieldsForChange.getPassword()));
        }
        currentMe.patch(fieldsForChange);
        return userService.save(currentMe);
    }
}
