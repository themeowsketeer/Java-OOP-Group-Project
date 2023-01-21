package com.project.server.dtos;

import lombok.*;

import java.util.Date;

@Data
@Builder
public class UserDto {
    private long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
}
