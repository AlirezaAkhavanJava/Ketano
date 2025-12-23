package com.arcade.ketano.config;

import org.springframework.lang.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * AuditorAware implementation for Spring Data JPA auditing.
 * <p>
 * Provides the current auditor (user) for @CreatedBy and @LastModifiedBy fields.
 * Integrates with Spring Security to automatically populate the username of
 * the authenticated user. Falls back to "SYSTEM" for unauthenticated or
 * anonymous users.
 * <p>
 * This class is referenced by @EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
 * in the main application configuration.
 */

@Component("auditorAwareImpl")
// Registers this bean with Spring and gives it a specific name for JPA auditing reference
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current auditor (the user performing the action) as a non-null Optional.
     *
     * @return Optional containing the current username, or "SYSTEM" if no authenticated user exists.
     */

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        // Retrieve the current Authentication object from Spring Security
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        // Case 1: No authentication present or not authenticated
        // This can happen for system jobs, anonymous users, or unauthenticated requests
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of("SYSTEM");
        }

        // Case 2: Authentication is an anonymous token (Spring Security default for anonymous users)
        // This prevents storing "anonymousUser" as the auditor
        if (authentication instanceof AnonymousAuthenticationToken) {
            return Optional.of("SYSTEM");
        }

        // Case 3: Authenticated user
        // Return the username of the currently logged-in user
        return Optional.of(authentication.getName());
    }
}

