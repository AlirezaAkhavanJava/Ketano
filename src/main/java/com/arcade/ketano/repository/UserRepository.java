package com.arcade.ketano.repository;

import com.arcade.ketano.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByNameContainingIgnoreCase(String name);

    Optional<User> findUserByEmailContainingIgnoreCase(String email);
}
