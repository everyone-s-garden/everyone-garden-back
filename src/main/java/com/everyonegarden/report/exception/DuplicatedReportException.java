package com.everyonegarden.report.exception;

import com.everyonegarden.global.error.ErrorCode;

public class DuplicatedReportException extends RuntimeException{
    private final ErrorCode errorCode;

    public DuplicatedReportException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
