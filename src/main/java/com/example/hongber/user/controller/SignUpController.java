package com.example.hongber.user.controller;

import com.example.hongber.common.controller.BaseController;
import com.example.hongber.common.response.RestResponseMessage;
import com.example.hongber.user.dto.SignUpReqDTO;
import com.example.hongber.user.entity.UserET;
import com.example.hongber.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SignUpController extends BaseController {
    private final SignUpService signUpService;

    @PostMapping("signUp")
    public ResponseEntity<RestResponseMessage<Long>> SignUp(@Validated @RequestBody SignUpReqDTO signUpReqDTO) {
        RestResponseMessage<Long> data = new RestResponseMessage<>();
        data.setData(signUpService.SignUp(signUpReqDTO));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(data);
    }
}
