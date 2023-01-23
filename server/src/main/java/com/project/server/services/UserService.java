package com.project.server.services;

import com.project.server.daos.UserEntity;
import com.project.server.dtos.AddUserDto;
import com.project.server.dtos.UserDto;
import com.project.server.exceptions.RecordNotFoundException;
import com.project.server.mappers.UserMapper;
import com.project.server.repository.RoleRepository;
import com.project.server.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAll() {
        return userMapper.map(userRepository.findAll());
    }

    public UserDto getUserById(long  id) {
        return userRepository.findById(id)
                .map(userMapper::map)
                .orElseThrow(() -> new RecordNotFoundException("User Not Found"));
    }

    @Transactional
    public UserDto addUser(AddUserDto user) {
        UserEntity entity = userMapper.map(user);
        var roles = entity.getRoles()
                .stream()
                .map(role -> roleRepository.findRoleByName(role.getName()).get())
                .collect(Collectors.toSet());
        entity.setRoles(roles);
        return userMapper.map(userRepository.save(entity));
    }
}
