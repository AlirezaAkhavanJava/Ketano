package com.arcade.ketano.model.dto;

import lombok.Builder;


@Builder
public record UserSearchResponseDto(
        String name,
        UserDashDto dashDto
) {
}
