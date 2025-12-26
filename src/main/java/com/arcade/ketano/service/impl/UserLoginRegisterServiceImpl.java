package com.arcade.ketano.service.impl;

import com.arcade.ketano.error.exceptions.EmailAlreadyExistsException;
import com.arcade.ketano.error.exceptions.UsernameAlreadyExistsException;
import com.arcade.ketano.model.dto.LoginRegisterResponse;
import com.arcade.ketano.model.dto.UserDto;
import com.arcade.ketano.model.entities.User;
import com.arcade.ketano.model.entities.UserDashBoard;
import com.arcade.ketano.model.mappers.UserMapper;
import com.arcade.ketano.repository.UserLoginRegisterRepository;
import com.arcade.ketano.service.UserLoginRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link UserLoginRegisterService} responsible for handling
 * user registration and related business logic.
 *
 * <p>This service performs validation of unique constraints (username and email),
 * maps DTOs to entities, initializes a default {@link UserDashBoard} for new users,
 * persists the user, and returns a response suitable for login/registration flows.</p>
 *
 * <p>All operations that modify data are executed within a transaction to ensure
 * data consistency.</p>
 *
 * @author [Your Name/Team]
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class UserLoginRegisterServiceImpl implements UserLoginRegisterService {

    /** Repository for user persistence and uniqueness queries. */
    private final UserLoginRegisterRepository userLoggingRepository;

    /** Mapper utility for converting between DTOs and entities. */
    private final UserMapper userMapper;

    // ==================== USER REGISTRATION ====================

    /**
     * Registers a new user based on the provided {@link UserDto}.
     *
     * <p>The method performs the following steps:
     * <ol>
     *   <li>Checks if the desired username is already taken.</li>
     *   <li>Checks if the provided email is already registered.</li>
     *   <li>Converts the {@link UserDto} to a {@link User} entity.</li>
     *   <li>Creates and attaches a default {@link UserDashBoard} with initial values.</li>
     *   <li>Persists the new user to the database.</li>
     *   <li>Maps the saved entity to a {@link LoginRegisterResponse} for the client.</li>
     * </ol>
     *
     * @param userDto the data transfer object containing user registration details
     * @return {@link LoginRegisterResponse} containing information needed after successful registration
     *         (typically used for immediate login)
     * @throws UsernameAlreadyExistsException if a user with the same username already exists
     * @throws EmailAlreadyExistsException    if a user with the same email already exists
     */
    @Override
    @Transactional
    public LoginRegisterResponse save(UserDto userDto) {
        // Validate username uniqueness
        if (userLoggingRepository.existsUserByName(userDto.name())) {
            throw new UsernameAlreadyExistsException(
                    "Username '" + userDto.name() + "' is already taken.");
        }

        // Validate email uniqueness
        if (userLoggingRepository.existsUserByEmail(userDto.email())) {
            throw new EmailAlreadyExistsException(
                    "Email '" + userDto.email() + "' is already registered.");
        }

        // Map DTO to entity
        User user = userMapper.toUser(userDto);

        // Initialize a default dashboard for the new user
        // - fullName is copied from the username (or registration name)
        // - age defaults to 0 (can be updated later)
        // - books list starts empty
        user.setDashboard(
                UserDashBoard.builder()
                        .fullName(user.getName())
                        .age(0)
                        .books(List.of())
                        .build()
        );

        // Persist the user entity (including cascaded dashboard)
        User saved = userLoggingRepository.save(user);

        // Convert saved entity to response DTO suitable for login/registration success
        return userMapper.toLoginResponse(saved);
    }
}