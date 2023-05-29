package com.alpaco.dev.mapper;

import com.alpaco.dev.dto.user.UserSignUpRequestDto;
import com.alpaco.dev.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void saveUser(User user);
    int checkEmail(String email);
    Optional<UserSignUpRequestDto> findUserByEmail(String email);

}
