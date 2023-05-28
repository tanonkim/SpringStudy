package com.alpaco.dev.mapper;

import com.alpaco.dev.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void saveUser(User user);
    int checkEmail(String email);
}
