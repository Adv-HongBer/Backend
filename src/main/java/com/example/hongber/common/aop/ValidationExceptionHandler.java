package com.example.hongber.common.aop;

import com.example.hongber.common.exception.validation.RestResponse;
import com.example.hongber.common.exception.validation.RestResponseMessage;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<RestResponse> processValidationError(MethodArgumentNotValidException ex) {
        List<RestResponseMessage> resList = new ArrayList<>();
        RestResponse restResponse = new RestResponse();

        ex.getBindingResult().getFieldErrors().forEach(i -> {
            RestResponseMessage res = new RestResponseMessage();

            res.setTarget(i.getField());
            res.setMessage(i.getDefaultMessage());

            resList.add(res);
        });

        restResponse.setMessage(resList);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }
}