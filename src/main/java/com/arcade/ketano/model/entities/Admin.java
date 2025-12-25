package com.arcade.ketano.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @NotEmpty
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;
    @NotEmpty
    @NotNull
    @Column(nullable = false, unique = true)
    private String password;
    @NotEmpty
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;
}
