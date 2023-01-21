package com.project.server.mappers;

import com.project.server.daos.User;
import com.project.server.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {
    UserDto map(User user);
    User map(UserDto user);
    List<UserDto> map(List<User> users);

    @Mapping(target = "id", ignore = true)
    void mapTo(@MappingTarget User entity, UserDto user);
}