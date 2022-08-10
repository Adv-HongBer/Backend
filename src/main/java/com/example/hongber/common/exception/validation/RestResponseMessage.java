package com.example.hongber.common.exception.validation;

import lombok.Data;

@Data
public class RestResponseMessage {
    private String target;
    private String message;
}