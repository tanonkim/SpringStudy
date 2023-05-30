package com.alpaco.dev.dto.user;

import com.alpaco.dev.entity.user.Status;
import com.alpaco.dev.entity.user.User;
import com.alpaco.dev.entity.user.oauth.OauthProvider;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class UserSignUpRequestDto {
    @NotBlank(message = "username은 필수 입력사항 입니다.")
    private String username;
    @NotBlank(message = "nickname은 필수 입력사항 입니다.")
    private String nickname;
    private String birth;
    @NotBlank(message = "이메일은 필수 입력사항 입니다.")
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?", message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력사항 입니다.")
    @Pattern(regexp = "^(?=.*[~!@#$%^&*()_+])[A-Za-z0-9~!@#$%^&*()_+]{8,}",
            message = "특수문자 포함 8자 이상 입력해야 합니다.")
    private String password;
    private boolean marketingAgreement;
    private OauthProvider oauthProvider;

    public User toEntity(){
        return User.builder()
                .username(username)
                .nickname(nickname)
                .birth(birth)
                .email(email)
                .password(password)
                .status(Status.ACTIVE)
                .privacyAgreement(true)
                .marketingAgreement(marketingAgreement)
                .hostPermission(false)
                .oauthProvider(OauthProvider.NONE)
                .build();
    }
}
