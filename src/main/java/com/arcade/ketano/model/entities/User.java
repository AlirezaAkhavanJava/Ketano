package com.arcade.ketano.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
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

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_table") // Explicit table name in DB coz user is reserved in PostgreSQL
@EqualsAndHashCode(callSuper = true) // Include BaseEntity fields in equality checks
@EntityListeners(AuditingEntityListener.class) // Enables JPA auditing for this entity
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Length(max = 100)
    @NotBlank

    private String name;

    @Column(nullable = false, unique = true)
    @Email
    @Size(min = 12, max = 300)
    @NotBlank
    @JsonProperty(value = "mail", required = true)
    private String email;

    @Column(nullable = false)
    @Size(min = 8, max = 50)
    @NotBlank
    private String password;
}
