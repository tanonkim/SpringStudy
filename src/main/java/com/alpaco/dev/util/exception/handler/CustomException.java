package com.alpaco.dev.util.exception.handler;

import com.alpaco.dev.util.BaseResponse;
import com.alpaco.dev.util.exception.RoomException;
import com.alpaco.dev.util.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.alpaco.dev.util.BaseResponseStatus.EMPTY_REQUEST_PARAMETER;
import static com.alpaco.dev.util.BaseResponseStatus.INVALID_REQUEST;


@Slf4j
@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public BaseResponse<Object> handleRequestParameter() {
        return new BaseResponse<>(EMPTY_REQUEST_PARAMETER);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public BaseResponse<Object> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        log.info("errors = {}", e.getBindingResult().getFieldError().getDefaultMessage());

        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return new BaseResponse<>(INVALID_REQUEST, errors);
    }

    @ExceptionHandler({UserException.class})
    public BaseResponse<Object> handleUserException(UserException e) {
        return new BaseResponse<>(e.getStatus());
    }

    @ExceptionHandler({RoomException.class})
    public BaseResponse<Object> handleRoomException(RoomException e) {
        return new BaseResponse<>(e.getStatus());
    }

}
