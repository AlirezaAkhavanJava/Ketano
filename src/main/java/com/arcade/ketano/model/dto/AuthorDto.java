package com.arcade.ketano.model.dto;


import lombok.Builder;

@Builder
public record AuthorDto(
        String name,
        String biography
) {
}
