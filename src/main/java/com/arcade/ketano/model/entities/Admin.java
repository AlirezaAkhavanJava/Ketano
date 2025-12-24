package com.arcade.ketano.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

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
