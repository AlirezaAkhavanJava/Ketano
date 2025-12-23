package com.arcade.ketano.model.mappers;

import com.arcade.ketano.model.dto.LoginRegisterResponse;
import com.arcade.ketano.model.dto.UserDto;
import com.arcade.ketano.model.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserDto userDto) {
        return User.builder()
                .name(userDto.name())
                .password(userDto.password())
                .email(userDto.email())
                .build();
    }

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public LoginRegisterResponse toLoginResponse(User user) {
        return LoginRegisterResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
