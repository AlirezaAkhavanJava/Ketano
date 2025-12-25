package com.arcade.ketano.service;

import com.arcade.ketano.model.dto.UserSearchResponseDto;

import java.util.Optional;

public interface UserService {
    Optional<UserSearchResponseDto> findByName(String name);

    Optional<UserSearchResponseDto> findByEmail(String email);
}
