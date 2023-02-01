package com.project.server.dtos;

import lombok.Builder;
import lombok.Data;

/**
 * The DTO for the author object
 */
@Data
@Builder
public class AuthorDto {
    private Long id;
    private String name;
}
