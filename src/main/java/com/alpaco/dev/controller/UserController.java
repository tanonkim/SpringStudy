package com.alpaco.dev.controller;

import com.alpaco.dev.dto.UserSignInRequestDto;
import com.alpaco.dev.dto.UserSignInResponseDto;
import com.alpaco.dev.dto.UserSignUpRequestDto;
import com.alpaco.dev.dto.UserSignUpResponseDto;
import com.alpaco.dev.service.UserService;
import com.alpaco.dev.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/app")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public BaseResponse<UserSignUpResponseDto> signUp(@Valid @RequestBody UserSignUpRequestDto dto) {
        return new BaseResponse<>(userService.joinUser(dto));
    }

    @PostMapping("/signin")
    public BaseResponse<UserSignInResponseDto> signIn(@Valid @RequestBody UserSignInRequestDto dto) {
        UserSignInResponseDto signin = userService.signin(dto); // result가 true, false =>
        return new BaseResponse<>(signin);
    }

}
