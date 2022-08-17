package com.example.hongber.common.controller;

import com.example.hongber.common.response.RestResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class BaseController {
    // 200 OK
    public ResponseEntity<RestResponseMessage<Object>> setOk(Object data) {
        RestResponseMessage<Object> response = new RestResponseMessage<>();
        response.setData(data);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // 400 Bad Request
    public ResponseEntity<RestResponseMessage<Object>> setBadReq(Object data) {
        RestResponseMessage<Object> response = new RestResponseMessage<>();
        response.setData(data);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // 403 Forbidden
    public ResponseEntity<RestResponseMessage<Object>> setForbidden(Object data) {
        RestResponseMessage<Object> response = new RestResponseMessage<>();
        response.setData(data);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // 404 Not Found
    public ResponseEntity<RestResponseMessage<Object>> setNotFound(Object data) {
        RestResponseMessage<Object> response = new RestResponseMessage<>();
        response.setData(data);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    // 500 Internal Server Error
    public ResponseEntity<RestResponseMessage<Object>> setInternalServerError(Object data) {
        RestResponseMessage<Object> response = new RestResponseMessage<>();
        response.setData(data);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}