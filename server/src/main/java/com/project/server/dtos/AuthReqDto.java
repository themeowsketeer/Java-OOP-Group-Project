package com.project.server.dtos;

import lombok.Data;

/**
 * The DTO for an authentication request from the user
 */
@Data
public class AuthReqDto {
    private String username;
    private String password;
}
