package com.alpaco.dev.service;


import com.alpaco.dev.dto.UserSignUpRequestDto;
import com.alpaco.dev.dto.UserSignUpResponseDto;
import com.alpaco.dev.entity.user.User;
import com.alpaco.dev.mapper.UserMapper;
import com.alpaco.dev.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.alpaco.dev.util.BaseResponseStatus.DUPLICATED_EMAIL;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSignUpResponseDto joinUser(UserSignUpRequestDto userRequest) {

        validateDuplicateEmail(userRequest.getEmail());

        User user = userRequest.toEntity();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userMapper.saveUser(user);

        return UserSignUpResponseDto.builder()
                .id(user.getUser_id())
                .build();
    }

    private boolean validateDuplicateEmail(String email) {
        if (userMapper.checkEmail(email) > 0) {
            throw new UserException(DUPLICATED_EMAIL);
        }
        return true;
    }
}
