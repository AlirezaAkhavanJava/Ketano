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

    @GetMapping("/{name}")
    public ResponseEntity<UserSearchResponseDto> findUserByName(@PathVariable String name) {
        return userService.findByName(name).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
