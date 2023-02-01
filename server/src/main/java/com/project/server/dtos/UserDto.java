package com.project.server.dtos;

import com.project.server.daos.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The DTO for the user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String username;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private Set<Role> roles = new HashSet<>();
}
