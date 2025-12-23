package com.arcade.ketano.model.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {

    /**
     * Primary key of the entity.
     * Using UUID ensures globally unique identifiers.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * Timestamp when the entity was created.
     * Automatically populated by Spring Data JPA when @EnableJpaAuditing is active.
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * Timestamp when the entity was last modified.
     * Automatically updated by Spring Data JPA.
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    /**
     * Username of the user who created the entity.
     * Populated automatically by AuditorAware implementation.
     */
    @CreatedBy
    public String createdBy;

    /**
     * Username of the user who last modified the entity.
     * Populated automatically by AuditorAware implementation.
     */
    @LastModifiedBy
    public String lastModifiedBy;


}
