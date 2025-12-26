package com.arcade.ketano.service.impl;

import com.arcade.ketano.error.exceptions.EmailAlreadyExistsException;
import com.arcade.ketano.error.exceptions.UsernameAlreadyExistsException;
import com.arcade.ketano.model.dto.LoginRegisterResponse;
import com.arcade.ketano.model.dto.UserDto;
import com.arcade.ketano.model.entities.User;
import com.arcade.ketano.model.entities.UserDashBoard;
import com.arcade.ketano.model.mappers.UserMapper;
import com.arcade.ketano.repository.UserLoginRegisterRepository;
import com.arcade.ketano.service.UserLoginRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserLoginRegisterServiceImpl
        implements UserLoginRegisterService {

    private final UserLoginRegisterRepository userLoggingRepository;
    private final UserMapper userMapper;

    // ================= REGISTER NEW USER =========================
    @Override
    @Transactional
    public LoginRegisterResponse save(UserDto userDto) {
        if (userLoggingRepository.existsUserByName(userDto.name())) {
            throw new UsernameAlreadyExistsException("Username '" + userDto.name() + "' is already taken.");
        }
        if (userLoggingRepository.existsUserByEmail(userDto.email())) {
            throw new EmailAlreadyExistsException("Email '" + userDto.email() + "' is already registered.");
        }

        User user = userMapper.toUser(userDto);
        user.setDashboard(
                UserDashBoard.builder()
                        .fullName(user.getName())
                        .age(0)
                        .books(List.of())
                        .build()
        );
        User saved = userLoggingRepository.save(user);
        return userMapper.toLoginResponse(saved);

    }
}
