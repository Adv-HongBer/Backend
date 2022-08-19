package com.example.hongber.common.controller;

import com.example.hongber.common.response.RestResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Slf4j
public class BaseController {
    // 200 OK
    public <T> ResponseEntity<RestResponseMessage<T>> setOk(T data) {
        RestResponseMessage<T> response = new RestResponseMessage<>();
        response.setData(data);

        ResponseEntity<RestResponseMessage<T>> res = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

        log.info("200 OK, result => {}", res);

        return res;
    }

    // 400 Bad Request
    public <T> ResponseEntity<RestResponseMessage<T>> setBadReq(T data) {
        RestResponseMessage<T> response = new RestResponseMessage<>();
        response.setData(data);

        ResponseEntity<RestResponseMessage<T>> res = ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(response);

        log.info("400 Bad Request, result => {}", res);

        return res;
    }

    // 403 Forbidden
    public <T> ResponseEntity<RestResponseMessage<T>> setForbidden(T data) {
        RestResponseMessage<T> response = new RestResponseMessage<>();
        response.setData(data);

        ResponseEntity<RestResponseMessage<T>> res = ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).body(response);

        log.info("403 Forbidden, result => {}", res);

        return res;
    }

    // 404 Not Found
    public <T> ResponseEntity<RestResponseMessage<T>> setNotFound(T data) {
        RestResponseMessage<T> response = new RestResponseMessage<>();
        response.setData(data);

        ResponseEntity<RestResponseMessage<T>> res = ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(response);

        log.info("404 Not Found, result => {}", res);

        return res;
    }

    // 500 Internal Server Error
    public <T> ResponseEntity<RestResponseMessage<T>> setISE(T data) {
        RestResponseMessage<T> response = new RestResponseMessage<>();
        response.setData(data);

        ResponseEntity<RestResponseMessage<T>> res = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);

        log.info("500 Internal Server Error, result => {}", res);

        return res;
    }
}