package com.alpaco.dev.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignInResponseDto {
    private String email;
    private String accessToken;
    private String refreshToken;
}
