package com.arcade.ketano.model.dto;



import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PublisherDto(
        String name,
        String description,
        LocalDate foundationDate
) {}
