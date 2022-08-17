package com.example.hongber.common.controller;

import com.example.hongber.common.response.RestResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class BaseController {
    // 200 OK
    public <T> ResponseEntity<RestResponseMessage<T>> setOk(T data) {
        RestResponseMessage<T> response = new RestResponseMessage<>();
        response.setData(data);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // 400 Bad Request
    public <T> ResponseEntity<RestResponseMessage<T>> setBadReq(T data) {
        RestResponseMessage<T> response = new RestResponseMessage<>();
        response.setData(data);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // 403 Forbidden
    public <T> ResponseEntity<RestResponseMessage<T>> setForbidden(T data) {
        RestResponseMessage<T> response = new RestResponseMessage<>();
        response.setData(data);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // 404 Not Found
    public <T> ResponseEntity<RestResponseMessage<T>> setNotFound(T data) {
        RestResponseMessage<T> response = new RestResponseMessage<>();
        response.setData(data);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // 500 Internal Server Error
    public <T> ResponseEntity<RestResponseMessage<T>> setISE(T data) {
        RestResponseMessage<T> response = new RestResponseMessage<>();
        response.setData(data);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}