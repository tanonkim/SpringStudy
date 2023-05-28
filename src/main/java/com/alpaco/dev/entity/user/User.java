package com.alpaco.dev.entity.user;

import com.alpaco.dev.entity.BaseTimeEntity;
import com.alpaco.dev.entity.user.oauth.OauthProvider;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    private long user_id;
    @NonNull
    private String username;
    @NonNull
    private String nickname;
    private String birth;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Status status;
    @NonNull
    private boolean privacyAgreement;
    @NonNull
    private boolean marketingAgreement;
    @NonNull
    private boolean hostPermission;
    private OauthProvider oauthProvider;


}
