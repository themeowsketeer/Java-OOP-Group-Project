package com.project.server.controllers;

import com.project.server.dtos.AddUserDto;
import com.project.server.dtos.UserDto;
import com.project.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public UserDto addUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> addUser(
            @RequestBody AddUserDto user
    ) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserDto addUserDto = userService.addUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addUserDto);
    }
}
