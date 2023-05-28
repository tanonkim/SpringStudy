package com.alpaco.dev.service;


import com.alpaco.dev.dto.UserSignInRequestDto;
import com.alpaco.dev.dto.UserSignInResponseDto;
import com.alpaco.dev.dto.UserSignUpRequestDto;
import com.alpaco.dev.dto.UserSignUpResponseDto;
import com.alpaco.dev.entity.user.User;
import com.alpaco.dev.mapper.UserMapper;
import com.alpaco.dev.util.exception.UserException;
import com.alpaco.dev.util.jwt.JwtProvider;
import com.alpaco.dev.util.jwt.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.alpaco.dev.util.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public UserSignUpResponseDto joinUser(UserSignUpRequestDto userRequest) {

        validateDuplicateEmail(userRequest.getEmail());

        User user = userRequest.toEntity(); // Dto -> Entity
        String encodedPassword = passwordEncoder.encode(user.getPassword()); // 비밀번호 암호화
        user.setPassword(encodedPassword);
        userMapper.saveUser(user); // user 저장

        return UserSignUpResponseDto.builder() // response build
                .id(user.getUser_id())
                .build();
    }

    public UserSignInResponseDto signin(UserSignInRequestDto userRequest) {
        User user = userMapper.findUserByEmail(userRequest.getEmail())
                .orElseThrow(() -> new UserException(NONE_EXIST_USER)).toEntity();

        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new UserException(INVALID_EMAIL_OR_PASSWORD);
        }

        String email = user.getEmail();
        Token token = jwtProvider.createToken(email);

        return UserSignInResponseDto.builder()
                .email(email)
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    private boolean validateDuplicateEmail(String email) {
        if (userMapper.checkEmail(email) > 0) {
            throw new UserException(DUPLICATED_EMAIL);
        }
        return true;
    }

}
