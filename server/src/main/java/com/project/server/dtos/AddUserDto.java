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
 * The DTO for adding a user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddUserDto {
    private long id;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private Set<Role> roles = new HashSet<>();

}
