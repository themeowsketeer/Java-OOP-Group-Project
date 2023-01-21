package com.project.server.services;

import com.project.server.daos.User;
import com.project.server.dtos.UserDto;
import com.project.server.mappers.UserMapper;
import com.project.server.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto findUserById(long id) {
        return userRepository.findById(id)
                .map(userMapper::map)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user with id = " + id));
    }

    @Transactional
    public UserDto addUser(UserDto user) {
        User entity = userMapper.map(user);
        return userMapper.map(userRepository.save(entity));

    }
}
