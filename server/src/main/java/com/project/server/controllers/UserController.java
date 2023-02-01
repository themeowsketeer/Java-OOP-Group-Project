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

/**
 * The controller for managing user REST API endpoints
 */
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

    /**
     * Handle GET request for the /api/users endpoint
     * @return A JSON representation for the list of DTOs of the user
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    /**
     * Handle GET request for /api/users{id} endpoint
     * @param id
     * @return A JSON representation of the DTO of a user
     */
    @GetMapping(value = "/{id}")
    public UserDto addUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    /**
     * Handle POST request for /api/users
     * @param user A JSON representation for the user
     * @return A JSON representation of the DTO of the added user with A response status of 201
     */
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
