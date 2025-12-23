package com.arcade.ketano.service;

import com.arcade.ketano.model.dto.LoginRegisterResponse;
import com.arcade.ketano.model.dto.UserDto;
import com.arcade.ketano.model.mappers.UserMapper;
import com.arcade.ketano.repository.UserLoginRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoginRegisterServiceImpl
        implements UserLoginRegisterService {

    private final UserLoginRegisterRepository userLoggingRepository;
    private final UserMapper userMapper;

    // ================= REGISTER NEW USER =========================
    @Override
    public LoginRegisterResponse save(UserDto userDto) {
        var user = userMapper.toUser(userDto);
        var result = userLoggingRepository.save(user);
        return userMapper.toLoginResponse(result);
    }
}
