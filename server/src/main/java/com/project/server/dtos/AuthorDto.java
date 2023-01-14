package com.project.server.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private Long id;
    private String name;
}
