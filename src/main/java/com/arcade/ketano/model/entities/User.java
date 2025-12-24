package com.arcade.ketano.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Represents a user in the system. This entity is central to authentication and authorization
 * processes, primarily used by Spring Security for user details and authentication.

 * Extends {@link BaseEntity} to inherit common fields such as id, createdAt, updatedAt, etc.
 * JPA auditing is enabled via {@link AuditingEntityListener} to automatically manage
 * creation and modification timestamps.

 * Note: Table name is explicitly set to "users_table" because "user" is a reserved keyword
 * in PostgreSQL.
 */
@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_table") // "user" is a reserved keyword in PostgreSQL, so custom name is used
@EqualsAndHashCode(callSuper = true) // Ensures BaseEntity fields (e.g., id) are included in equals/hashCode
@EntityListeners(AuditingEntityListener.class) // Enables automatic auditing (createdAt, updatedAt)
public class User extends BaseEntity {

    /**
     * The user's full name or display name.
     * Must be unique and cannot be blank.
     * Maximum length is 100 characters.
     */
    @Column(nullable = false, unique = true)
    @Length(max = 100)
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * The user's email address, used as a unique identifier for login and communication.
     * Must be a valid email format, not blank, and between 12 and 300 characters.
     * Serialized as "mail" in JSON responses for frontend compatibility.
     */
    @Column(nullable = false, unique = true)
    @Email(message = "Please provide a valid email address")
    @Size(min = 12, max = 300, message = "Email must be between 12 and 300 characters")
    @NotBlank(message = "Email is required")
    @JsonProperty(value = "mail", required = true) // Custom JSON property name for API consistency
    private String email;

    /**
     * The user's encrypted password.
     * Stored securely (never in plain text). Should be encoded using a strong password encoder
     * (e.g., BCryptPasswordEncoder) before persistence.
     * Minimum 8 characters, maximum 50 (sufficient for hashed values).
     */
    @Column(nullable = false)
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @NotBlank(message = "Password is required")
    private String password;

    /**
     * One-to-One relationship with the user's dashboard.
     * Represents personalized user data, settings, or summary statistics.
     * Lazy loading is used to improve performance by deferring dashboard data fetching
     * until explicitly accessed.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dashboard_id", referencedColumnName = "id")
    private UserDashBoard dashboard;
}