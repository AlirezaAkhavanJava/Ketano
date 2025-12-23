package com.arcade.ketano.service;

import com.arcade.ketano.model.dto.LoginRegisterResponse;
import com.arcade.ketano.model.dto.UserDto;
import jakarta.validation.Valid;

public interface UserLoginRegisterService {
    LoginRegisterResponse save(@Valid UserDto userDto);
}
