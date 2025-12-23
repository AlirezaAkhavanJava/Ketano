package com.arcade.ketano.controller;

import com.arcade.ketano.model.dto.LoginRegisterResponse;
import com.arcade.ketano.model.dto.UserDto;
import com.arcade.ketano.service.UserLoginRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/user")

public class LoginRegisterController {

    private final UserLoginRegisterService userLoginRegisterService;


    // ================= REGISTER NEW USER =========================
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LoginRegisterResponse> saveUser(@Valid @RequestBody UserDto userDto) {
        var result = userLoginRegisterService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
