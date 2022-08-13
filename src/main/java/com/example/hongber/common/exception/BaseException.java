package com.example.hongber.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException {
    private String message;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }
}
