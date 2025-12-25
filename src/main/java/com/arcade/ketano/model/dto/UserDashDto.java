package com.arcade.ketano.model.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record UserDashDto(
        String full_name,
        UUID id,
        List<BookDto> books
) {
}
