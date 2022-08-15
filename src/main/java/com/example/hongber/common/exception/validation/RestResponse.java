package com.example.hongber.common.exception.validation;

import lombok.Data;

import java.util.List;

@Data
public class RestResponse {
    private List<RestResponseMessage> message;
}