package com.project.server.mappers;

import com.project.server.daos.UserEntity;
import com.project.server.dtos.AddUserDto;
import com.project.server.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * The mapper between the DAO and the DTO for a user
 */
@Mapper(
        componentModel = "spring"
)
public interface UserMapper {
    UserDto map(UserEntity user);
    List<UserDto> map(List<UserEntity> user);
    UserEntity map(AddUserDto user);
    AddUserDto mapToAddUser(UserEntity user);
    @Mapping(ignore = true, target = "id")
    void mapTo(@MappingTarget UserEntity entity, UserDto user);
}
