package com.arcade.ketano.controller;

import com.arcade.ketano.model.dto.LoginRegisterResponse;
import com.arcade.ketano.model.dto.UserDto;
import com.arcade.ketano.service.UserLoginRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")

public class LoginRegisterController {

    private final UserLoginRegisterService userLoginRegisterService;

    // ================= REGISTER NEW USER =========================
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginRegisterResponse saveUser(@Valid @NonNull @RequestBody UserDto userDto) {
        return userLoginRegisterService.save(userDto);
    }


}
