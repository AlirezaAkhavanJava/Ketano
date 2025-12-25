package com.arcade.ketano.controller;

import com.arcade.ketano.model.dto.UserSearchResponseDto;
import com.arcade.ketano.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class Users {

    private final UserService userService;

    // ================================ GET BY NAME =====================================
    /**
     * Retrieves a user's profile information along with their dashboard and associated books
     * by performing a case-insensitive partial match on the user's name.
     *
     * <p>This endpoint is designed for user search functionality, allowing clients to find
     * users by typing part of their name (e.g., "joh" will match "John Doe").
     *
     * <p><strong>HTTP Status Codes:</strong>
     * <ul>
     *   <li><code>200 OK</code> - User found, returns {@link UserSearchResponseDto}</li>
     *   <li><code>404 Not Found</code> - No user found with the given name (partial match)</li>
     * </ul>
     *
     * <p><strong>Example:</strong>
     * <pre>
     * GET /users/John
     * GET /users/joh        → matches "John", "Johnny", etc. (case-insensitive)
     * </pre>
     *
     * @param name the name (or partial name) of the user to search for
     * @return ResponseEntity containing the {@link UserSearchResponseDto} if found,
     * or 404 Not Found if no matching user exists
     * @since 1.0.0
     */

    @GetMapping("/{name}")
    public ResponseEntity<UserSearchResponseDto> findUserByName(@PathVariable String name) {
        // Validate input to prevent unnecessary database queries
        if (name == null || name.trim().isBlank()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

        return userService.findByName(name.trim())
                .map(ResponseEntity::ok)                    // 200 OK with body
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    // ================================ GET BY Email =====================================
    /**
     * Retrieves a user's profile information along with their dashboard and associated books
     * by searching for an exact match on the user's email address.
     *
     * <p>This endpoint performs a <strong>case-insensitive exact match</strong> on the provided email.
     * It is typically used for user lookup during authentication flows, profile access,
     * or administrative searches where the full email is known.
     *
     * <p><strong>HTTP Status Codes:</strong>
     * <ul>
     *   <li><code>200 OK</code> - User found, returns {@link UserSearchResponseDto}</li>
     *   <li><code>404 Not Found</code> - No user found with the exact email provided</li>
     * </ul>
     *
     * <p><strong>Security Note:</strong>
     * This endpoint should be protected with proper authentication and authorization
     * (e.g., admin-only or same-user access) in production to prevent email enumeration attacks.
     *
     * <p><strong>Example Requests:</strong>
     * <pre>
     * GET /users/email/john.doe@example.com
     * GET /users/email/JOHN.DOE@EXAMPLE.COM  → matches due to case-insensitive comparison
     * </pre>
     *
     * @param email the email address of the user to search for (path variable)
     * @return ResponseEntity containing the {@link UserSearchResponseDto} if a matching user is found,
     *         or 404 Not Found if no user exists with that email
     * @since 1.0.0
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserSearchResponseDto> findUserByEmail(@PathVariable("email") String email) {
        // Early return for invalid/empty input to avoid unnecessary processing
        if (email == null || email.trim().isBlank()) {
            return ResponseEntity.notFound().build();
        }

        String normalizedEmail = email.trim().toLowerCase();

        return userService.findByEmail(normalizedEmail)
                .map(ResponseEntity::ok)                    // 200 OK with user data
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }
}
