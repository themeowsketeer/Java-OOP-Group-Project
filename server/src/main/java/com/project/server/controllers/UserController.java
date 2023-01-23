package com.project.server.controllers;

import com.project.server.dtos.AddUserDto;
import com.project.server.dtos.UserDto;
import com.project.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(com.project.server.services.UserService userService) {
        this.userService = userService;
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
        UserDto addUserDto = userService.addUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addUserDto);
    }
}
