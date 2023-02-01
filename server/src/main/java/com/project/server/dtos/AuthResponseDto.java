package com.project.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * The DTO for the authentication response
 */
@Data
@AllArgsConstructor
@Builder
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";
    private UserDto user;

    public AuthResponseDto(String accessToken, UserDto userResponse) {

        this.accessToken = accessToken;
        this.user = userResponse;
    }
}
