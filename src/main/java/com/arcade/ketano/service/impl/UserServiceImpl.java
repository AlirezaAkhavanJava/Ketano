package com.arcade.ketano.service.impl;

import com.arcade.ketano.model.dto.UserSearchResponseDto;
import com.arcade.ketano.model.mappers.UserResponseMapper;
import com.arcade.ketano.repository.UserRepository;
import com.arcade.ketano.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Implementation of {@link UserService} responsible for user lookup operations.
 * Provides methods to search users by name (partial match) or email (case-insensitive contains match).
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;

    /**
     * Searches for a user by partial, case-insensitive match on the name.
     *
     * <p>Example: Searching "joh" will match users named "John", "Johnny", "Johanna", etc.
     *
     * @param name the partial or full name to search for (trimmed and validated)
     * @return {@link Optional} containing {@link UserSearchResponseDto} if found, empty otherwise
     */
    @Override
    public Optional<UserSearchResponseDto> findByName(String name) {
        if (name == null || name.trim().isBlank()) {
            return Optional.empty();
        }

        String trimmedName = name.trim();

        return userRepository.findUserByNameContainingIgnoreCase(trimmedName)
                .map(userResponseMapper::toUserSearchResponse);
    }

    /**
     * Searches for a user by case-insensitive partial match on the email address.
     *
     * <p>The email is normalized to lowercase before querying to ensure consistent matching.
     * Example: "John.Doe@Example.com" will match stored email "john.doe@example.com".
     *
     * <p><strong>Note:</strong> Using "containing" allows partial matches (e.g., "@example.com").
     * If you want exact email matching only, change the repository method to {@code findByEmailIgnoreCase}.
     *
     * @param email the email (or part of it) to search for
     * @return {@link Optional} containing {@link UserSearchResponseDto} if found, empty otherwise
     */
    @Override
    public Optional<UserSearchResponseDto> findByEmail(String email) {
        if (email == null || email.trim().isBlank()) {
            return Optional.empty();
        }

        String normalizedEmail = email.trim().toLowerCase();

        return userRepository.findUserByEmailContainingIgnoreCase(normalizedEmail)
                .map(userResponseMapper::toUserSearchResponse);
    }
}
