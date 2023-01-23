package com.project.server.dtos;

import lombok.Data;

@Data
public class AuthReqDto {
    private String username;
    private String password;
}
