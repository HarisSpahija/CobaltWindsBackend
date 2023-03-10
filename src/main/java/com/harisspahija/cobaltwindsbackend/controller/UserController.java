package com.harisspahija.cobaltwindsbackend.controller;

import com.harisspahija.cobaltwindsbackend.dto.UserDto;
import com.harisspahija.cobaltwindsbackend.dto.UserInputDto;
import com.harisspahija.cobaltwindsbackend.exception.BadRequestBindingException;
import com.harisspahija.cobaltwindsbackend.security.JwtService;
import com.harisspahija.cobaltwindsbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String id) {
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserMe(@RequestHeader (name="Authorization") String token) {
        String username = jwtService.extractUsername(token.substring(7));
        UserDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserInputDto userInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestBindingException(bindingResult);
        }

        UserDto dto = userService.createUser(userInputDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }
}
