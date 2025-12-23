package com.arcade.ketano.controller;

import com.arcade.ketano.error.exceptions.UserExistException;
import com.arcade.ketano.model.dto.LoginRegisterResponse;
import com.arcade.ketano.model.dto.UserDto;
import com.arcade.ketano.service.UserLoginRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/user")

public class LoginRegisterController {

    private final UserLoginRegisterService userLoginRegisterService;


    /**
     * Registers a new user.
     * <p>
     * Validates the incoming {@link UserDto}, checks for duplicate username or email,
     * and creates a new user account. Returns the created user's details along with
     * a {@code 201 Created} status.
     * </p>
     * <p>
     * Possible exceptions:
     * <ul>
     *     <li>{@link UserExistException} - if username or email already exists</li>
     *     <li>Validation errors - handled by Spring's {@link jakarta.validation.Valid}</li>
     * </ul>
     * </p>
     *
     * @param userDto the user registration data (validated)
     * @return {@link ResponseEntity} containing the registered user details with HTTP 201
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LoginRegisterResponse> saveUser(@Valid @RequestBody UserDto userDto) {
        var result = userLoginRegisterService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
