package com.project.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";
    private UserDto user;

    public AuthResponseDto(String accessToken, UserDto userResponse) {

        this.accessToken = accessToken;
        this.user = userResponse;
    }
}
