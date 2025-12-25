package com.arcade.ketano.model.dto;



import lombok.Builder;

@Builder
public record InterpreterDto(
        String name,
        String biography
) {}
