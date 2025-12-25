package com.arcade.ketano.service.impl;

import com.arcade.ketano.model.dto.UserSearchResponseDto;
import com.arcade.ketano.model.mappers.UserResponseMapper;
import com.arcade.ketano.repository.UserRepository;
import com.arcade.ketano.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;

    @Override
    public Optional<UserSearchResponseDto> findByName(String name) {
        if (!Objects.isNull(name) && !name.isEmpty()) {
            return userRepository.findUserByNameContainingIgnoreCase(name)
                    .map(userResponseMapper::toUserSearchResponse);
        }
        return Optional.empty();
    }
}
