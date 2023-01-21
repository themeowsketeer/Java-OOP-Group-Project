package com.project.server.services;

import com.project.server.daos.User;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<String> login(String username, String password);
    Optional<User> findByToken(String token);
    void logout();
}
