package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.User;
import com.jk.it_one.requestDtos.AuthorisationDto;
import com.jk.it_one.requestDtos.RegistrationDto;
import com.jk.it_one.requestDtos.UserPatchDto;
import com.jk.it_one.security.JWTCore;
import com.jk.it_one.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

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
    ResponseEntity<?> createUser(@RequestBody RegistrationDto registrationDto) {
        if (userService.existsByUsername(registrationDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        registrationDto.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        User me = new User(registrationDto);
        userService.save(me);
        return ResponseEntity.status(HttpStatus.CREATED).body(me);
    }

    @PostMapping("/auth/token/login")
    ResponseEntity<?> signIn (@RequestBody AuthorisationDto authorisationDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authorisationDto.getUsername(),
                            authorisationDto.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/profile")
    ResponseEntity<?> getProfile(Principal principal, @RequestParam Currency currency) {
        return ResponseEntity.ok(userService.getUserProfile(principal, currency));
    }

    @PatchMapping("/users/me")
    ResponseEntity<?> updateUser(Principal principal, @RequestBody UserPatchDto fieldsForChange) {
        User currentMe = userService.findMe(principal);
        currentMe.setName(Objects.requireNonNullElse(fieldsForChange.getName(), currentMe.getName()));
        if (fieldsForChange.getPassword() != null) {
            fieldsForChange.setPassword(passwordEncoder.encode(fieldsForChange.getPassword()));
        }
        return ResponseEntity.ok(userService.save(currentMe));
    }
}
