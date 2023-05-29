package com.alpaco.dev.controller;

import com.alpaco.dev.dto.user.UserSignInRequestDto;
import com.alpaco.dev.dto.user.UserSignInResponseDto;
import com.alpaco.dev.dto.user.UserSignUpRequestDto;
import com.alpaco.dev.dto.user.UserSignUpResponseDto;
import com.alpaco.dev.service.UserService;
import com.alpaco.dev.service.auth.KakaoUserService;
import com.alpaco.dev.util.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/app")
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;


    @PostMapping("/signup")
    public BaseResponse<UserSignUpResponseDto> signUp(@Valid @RequestBody UserSignUpRequestDto dto) {
        return new BaseResponse<>(userService.joinUser(dto));
    }

    @PostMapping("/signin")
    public BaseResponse<UserSignInResponseDto> signIn(@Valid @RequestBody UserSignInRequestDto dto) {
        UserSignInResponseDto signin = userService.signin(dto); // result가 true, false =>
        return new BaseResponse<>(signin);
    }

    @GetMapping("/user/kakao/callback")
    public BaseResponse<UserSignInResponseDto> kakaoLogin(@RequestParam String code,
                                                          HttpServletResponse response) throws JsonProcessingException {
        UserSignInResponseDto signin = kakaoUserService.kakaoLogin(code, response);
        return new BaseResponse<>(signin);
    }
}
