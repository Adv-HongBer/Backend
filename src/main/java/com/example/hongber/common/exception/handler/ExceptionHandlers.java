package com.example.hongber.common.exception.handler;

import com.example.hongber.common.exception.BaseException;
import com.example.hongber.common.exception.validation.RestResponse;
import com.example.hongber.common.exception.validation.RestResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {
    // 기본
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<RestResponseMessage> handleException(Exception e) {
        RestResponseMessage restResponseMessage = new RestResponseMessage();
        restResponseMessage.setMessage("시스템에 오류가 발생하였습니다. 해당 문제가 지속된다면 관리자에게 문의해 주시기 바랍니다.");

        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(restResponseMessage);
    }

    // validation
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<RestResponse> processValidationError(MethodArgumentNotValidException e) {
        List<RestResponseMessage> resList = new ArrayList<>();
        RestResponse restResponse = new RestResponse();
        e.getBindingResult().getFieldErrors().forEach(i -> {
            RestResponseMessage res = new RestResponseMessage();
            res.setTarget(i.getField());
            res.setMessage(i.getDefaultMessage());
            resList.add(res);
        });
        restResponse.setMessage(resList);

        log.error(resList.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(restResponse);
    }

    // base
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<RestResponseMessage> handleBaseException(BaseException e) {
        RestResponseMessage restResponseMessage = new RestResponseMessage();
        restResponseMessage.setMessage(e.getMessage());

        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(restResponseMessage);
    }
}
