package com.arcade.ketano.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record UserDto(
        @NonNull
        @NotBlank(message = "Blank filed is not allowed")
        @NotEmpty(message = "This field can not be empty")
        @Size(max = 100)
        String name,

        @NonNull
        @NotBlank(message = "Blank filed is not allowed")
        @NotEmpty(message = "This field can not be empty")
        @Email
        @Size(min = 12, max = 300)
        String email,

        @NonNull
        @NotBlank(message = "Blank filed is not allowed")
        @NotEmpty(message = "This field can not be empty")
        @Size(min = 8, max = 50)
        String password
) {
}
