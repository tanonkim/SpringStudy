package com.alpaco.dev.util.exception;

import com.alpaco.dev.util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private final BaseResponseStatus status;

    public UserException(BaseResponseStatus status) {
        this.status = status;
    }
}
