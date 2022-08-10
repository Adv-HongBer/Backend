package com.example.hongber.common.exception.validation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
public class RestResponse {
    @Value("false")
    private Boolean success;
    private List<RestResponseMessage> message;
}