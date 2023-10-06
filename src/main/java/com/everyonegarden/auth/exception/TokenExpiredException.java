package com.everyonegarden.auth.exception;

import com.everyonegarden.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class TokenExpiredException extends RuntimeException {

    private final ErrorCode errorCode;

    public TokenExpiredException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
