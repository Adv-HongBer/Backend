package com.example.hongber.user.controller;

import com.example.hongber.common.controller.BaseController;
import com.example.hongber.common.response.RestResponseMessage;
import com.example.hongber.user.dto.SignInReqDTO;
import com.example.hongber.user.entity.UserET;
import com.example.hongber.user.service.SignInService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class SignInController extends BaseController {
    private final SignInService signInService;

    @PostMapping("signIn")
    public ResponseEntity<RestResponseMessage<UserET>> SignIn(@Validated @RequestBody SignInReqDTO signInReqDTO) {
        return setOk(signInService.signIn(signInReqDTO));
    }
}
