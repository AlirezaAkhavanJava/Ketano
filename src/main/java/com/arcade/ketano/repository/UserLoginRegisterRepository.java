package com.arcade.ketano.repository;

import com.arcade.ketano.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserLoginRegisterRepository
        extends JpaRepository<User, UUID> {
    boolean existsUserByName(String name);

    boolean existsUserByEmail(String email);


}
