package com.alpaco.dev.service.auth;

import com.alpaco.dev.entity.user.User;
import com.alpaco.dev.entity.user.auth.PrincipalDetails;
import com.alpaco.dev.mapper.UserMapper;
import com.alpaco.dev.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.alpaco.dev.util.BaseResponseStatus.NONE_EXIST_USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public PrincipalDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.findUserByEmail(email)
                .orElseThrow(() -> new UserException(NONE_EXIST_USER)).toEntity();

        log.info("loadUserByUsername, user={}", user.getEmail());

        return new PrincipalDetails(user);
    }
}
