package com.project.server.controllers;

import com.project.server.daos.Role;
import com.project.server.daos.UserEntity;
import com.project.server.dtos.AuthReqDto;
import com.project.server.dtos.AuthResponseDto;
import com.project.server.mappers.UserMapper;
import com.project.server.repository.RoleRepository;
import com.project.server.repository.UserRepository;
import com.project.server.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * The controller for authentication service, which includes
 * login and register
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider tokenProvider;
    @Autowired
    private UserMapper mapper;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Handle login request from the user at the endpoint: /api/auth/login
     * @param loginDto: a JSON representation for the authentication request object
     * @return the authentication response object which contains access token,
     * token type, and the current user with the HTTP status OK
     */
    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody AuthReqDto loginDto
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
//        Hold authentication information in the context
//        So that user doesn't have to login again
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        UserEntity entity = userRepository.findByUsername(loginDto.getUsername()).get();
        return new ResponseEntity<>(new AuthResponseDto(token, mapper.map(entity)), HttpStatus.OK);
    }

    /**
     * Handle register request from the user at the enpoint: /api/auth/register
     * @param authReqDto: a JSON representation for the authentication request object
     * @return a response message with the HTTP status Created
     */
    @PostMapping("register")
    public ResponseEntity<String> register(
            @RequestBody AuthReqDto authReqDto
    ) {
        if (userRepository.existsByUsername(authReqDto.getUsername())) {
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        Role roles = roleRepository.findRoleByName("USER").get();

        UserEntity user = UserEntity
                .builder()
                .username(authReqDto.getUsername())
                .password(passwordEncoder.encode(authReqDto.getPassword()))
                .roles(Collections.singleton(roles))
                .build();
        userRepository.save(user);

        return new ResponseEntity<>("User registered success", HttpStatus.CREATED);
    }
}
