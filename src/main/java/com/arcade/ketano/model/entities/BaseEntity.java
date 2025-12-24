package com.arcade.ketano.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Abstract base class for all JPA entities in the application.
 * Provides common fields and auditing capabilities that should be inherited by all entities.
 *
 * <p>Key features:
 * <ul>
 *   <li>UUID-based primary key for distributed system safety and uniqueness</li>
 *   <li>Automatic creation and modification timestamps via Spring Data JPA Auditing</li>
 *   <li>Tracking of who created and last modified the entity (requires AuditorAware configuration)</li>
 * </ul>
 *
 * <p>To enable auditing, ensure:
 * <ul>
 *   <li>@EnableJpaAuditing is present on a configuration class</li>
 *   <li>An AuditorAware<String> bean is defined (typically returning the current authenticated username)</li>
 * </ul>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {

    /**
     * Unique identifier for the entity.
     * Uses UUID to avoid sequential ID vulnerabilities and ensure global uniqueness
     * across distributed systems or database sharding.
     *
     * <p>GenerationType.AUTO allows "Hibernate" to choose the most appropriate strategy
     * based on the underlying database (typically UUID for PostgreSQL).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;

    /**
     * Timestamp indicating when this entity was first persisted.
     * Automatically set by Spring Data JPA when the entity is first saved.
     *
     * <p>Column is non-nullable and non-updatable for data integrity.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp indicating the last time this entity was modified.
     * Automatically updated by Spring Data JPA on every update.
     *
     * <p>Initially set to creation time.
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedAt;

    /**
     * Username or identifier of the user who created this entity.
     * Populated automatically via {@link CreatedBy} and a configured {@link org.springframework.data.domain.AuditorAware} bean.
     *
     * <p>Useful for audit trails and accountability.
     */
    @CreatedBy
    @Column(nullable = false, updatable = false, length = 100)
    private String createdBy;

    /**
     * Username or identifier of the user who last modified this entity.
     * Updated automatically on every save after creation.
     */
    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String lastModifiedBy;

    /**
     * Pre-persist lifecycle callback to ensure timestamps and audit fields are initialized properly.
     * This acts as a safety net in case auditing is not fully configured.
     */
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.lastModifiedAt = now;
        // Note: createdBy and lastModifiedBy should ideally be set via AuditorAware
        // This is a fallback only if needed
    }

    /**
     * Pre-update lifecycle callback to ensure lastModifiedAt is updated.
     */
    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedAt = LocalDateTime.now();
    }
}